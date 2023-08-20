package com.onna.onnaback.domain.apply.domain;

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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "MemberSparkMapping")
@Getter
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

    @Builder
    public MemberSparkMapping(
            Member applicant,
            Spark applySpark,
            AcceptStatus acceptStatus
    ) {
        this.applicant = applicant;
        this.applySpark = applySpark;
        this.acceptStatus = acceptStatus;
    }
}
