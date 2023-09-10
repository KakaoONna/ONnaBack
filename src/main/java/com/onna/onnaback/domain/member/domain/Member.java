package com.onna.onnaback.domain.member.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.global.utils.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "profileImg")
    private String profileImg;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    //생일(월-일) MMDD
    @Column(name = "birthDate")
    private String birthDay;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ageRange")
    private Age ageRange;

    @Column(name = "refreshToken")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "host")
    List<Spark> sparkList = new ArrayList<>();

    @OneToMany(mappedBy = "applicant")
    List<MemberSparkMapping> memberSparkMappingList = new ArrayList<>();

    @Builder
    public Member(Long memberId,String name, Age ageRange, String email, Role role,
                  String refreshToken, String profileImg, String birthDay, Gender gender) {
        this.memberId=memberId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.ageRange = ageRange;
        this.profileImg = profileImg;
        this.birthDay = birthDay;
        this.gender = gender;
        this.refreshToken=refreshToken;
    }
    @Builder
    public Member(Long memberId,String profileImg) {

        this.memberId=memberId;
        this.profileImg=profileImg;
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}
