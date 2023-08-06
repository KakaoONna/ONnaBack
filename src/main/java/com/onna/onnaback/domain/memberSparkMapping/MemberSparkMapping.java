package com.onna.onnaback.domain.memberSparkMapping;


import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.Spark;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "MemberSparkMapping")
public class MemberSparkMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberSparkMappingId")
    private Long memberSparkMappingId;

    // [연관관계] 1(Member) : N (MemberSparkMapping)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="member_memberId")
    private Member applicant;

    // [연관관계] 1(Sparkr) : N (MemberSparkMapping)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="sparkId")
    private Spark applySpark;


    @Enumerated(value = EnumType.STRING)
    private AcceptStatus acceptStatus;
    private enum AcceptStatus{
        PENDING,ACCEPT,REFUSE
    }
}
