package com.onna.onnaback.domain.member.domain;

import com.onna.onnaback.domain.memberSparkMapping.MemberSparkMapping;
import com.onna.onnaback.domain.spark.Spark;
import com.onna.onnaback.global.utils.BaseEntity;
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
public class Member extends BaseEntity {
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


    @OneToMany(mappedBy = "host")
    List<Spark> sparkList=new ArrayList<>();


    @OneToMany(mappedBy = "applicant")
    List<MemberSparkMapping> memberSparkMappingList=new ArrayList<>();



}
