package com.onna.onnaback.domain.member.domain;

import com.onna.onnaback.domain.memberSparkMapping.MemberSparkMapping;
import com.onna.onnaback.domain.spark.Spark;

import com.onna.onnaback.global.utils.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Member")

public class Member extends BaseEntity implements UserDetails {


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
    private String birthDay;
    @Enumerated(value = EnumType.STRING)
    @Column(name="ageRange")
    private Age ageRange;
    @Column(name="phoneNum")
    private String phoneNum;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name="refreshToken")
    private String refreshToken;

    //oauth2
    @Enumerated(EnumType.STRING)
    @Column(name = "socialType")
    private SocialType socialType;
    @Column(name = "socialId")
    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    @OneToMany(mappedBy = "host")
    List<Spark> sparkList=new ArrayList<>();



    @OneToMany(mappedBy = "applicant")
    List<MemberSparkMapping> memberSparkMappingList=new ArrayList<>();

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @Builder
    public Member(String name,Age ageRange,String email,Role role,SocialType socialType,String socialId,String phoneNum,String profileImg,String birthDay,Gender gender){
        this.name=name;
        this.email=email;
        this.role=role;
        this.socialType=socialType;
        this.socialId=socialId;
        this.ageRange=ageRange;
        this.phoneNum=phoneNum;
        this.profileImg=profileImg;
        this.birthDay=birthDay;
        this.gender=gender;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(Role.USER.toString()));
        return auth;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
