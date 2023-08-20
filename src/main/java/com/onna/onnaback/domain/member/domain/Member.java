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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Member extends BaseEntity implements UserDetails {

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

    @Column(name = "phoneNum")
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "refreshToken")
    private String refreshToken;

    //oauth2
    @Enumerated(EnumType.STRING)
    @Column(name = "socialType")
    private SocialType socialType;

    @Column(name = "socialId")
    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    @OneToMany(mappedBy = "host")
    List<Spark> sparkList = new ArrayList<>();

    @OneToMany(mappedBy = "applicant")
    List<MemberSparkMapping> memberSparkMappingList = new ArrayList<>();

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @Builder
    public Member(Long memberId,String name, Age ageRange, String email, Role role, SocialType socialType, String socialId,
                  String phoneNum, String profileImg, String birthDay, Gender gender) {
        this.memberId=memberId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.socialType = socialType;
        this.socialId = socialId;
        this.ageRange = ageRange;
        this.phoneNum = phoneNum;
        this.profileImg = profileImg;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    @Override
    public ArrayList<GrantedAuthority> getAuthorities() {
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
