package com.onna.onnaback.domain.member.domain;

import com.onna.onnaback.domain.memberSparkMapping.MemberSparkMapping;
import com.onna.onnaback.domain.spark.Spark;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long memberId;

    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="profileImg")
    private String profileImg;
    @Enumerated(value = EnumType.STRING)
    @Column(name="gender")
    private Gender gender;
    @Column(name="birthDate")
    private LocalDateTime birthDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name="ageRange")
    private Age ageRange;
    @Column(name="phoneNum")
    private String phoneNum;

    //[연관관계] 1(Member) : N(Spark)
    @OneToMany(mappedBy = "host")
    List<Spark> sparkList=new ArrayList<>();

    // [연관관계] 1(Member) : N (MemberSparkMapping)
    @OneToMany(mappedBy = "applicant")
    List<MemberSparkMapping> memberSparkMappingList=new ArrayList<>();



    private enum Gender{
        MAN,WOMAN
    }

    private enum Age{
        TEENS,TWENTIES,THIRTIES,FORTIES,FIFTIES,SIXTIES,SEVENTIES
    }

}
