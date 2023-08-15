package com.onna.onnaback.domain.memberSparkMapping.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.global.utils.BaseEntity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "MemberSparkMapping")

public class MemberSparkMapping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberSparkMappingId")
    private Long memberSparkMappingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_memberId")
    private Member applicant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sparkId")
    private Spark applySpark;

    @Enumerated(value = EnumType.STRING)
    private AcceptStatus acceptStatus;

}
