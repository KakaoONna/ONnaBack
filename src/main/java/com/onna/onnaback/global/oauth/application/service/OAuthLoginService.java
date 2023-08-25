package com.onna.onnaback.global.oauth.application.service;

import com.onna.onnaback.domain.member.adapter.out.persistence.MemberRepository;
import com.onna.onnaback.domain.member.domain.Age;
import com.onna.onnaback.domain.member.domain.Gender;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.member.domain.Role;
import com.onna.onnaback.global.exception.BaseException;
import com.onna.onnaback.global.oauth.adapter.in.web.response.kakao.KakaoInfoResponse;
import com.onna.onnaback.global.oauth.adapter.in.web.response.kakao.KakaoLoginRequest;
import com.onna.onnaback.global.oauth.adapter.in.web.response.OAuthLoginResponse;
import com.onna.onnaback.global.oauth.adapter.in.web.response.kakao.KakaoProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthLoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final OAuthService oAuthService;

    private final JwtService jwtService;


    public OAuthLoginResponse login(String authorizationCode) {

        String accessToken = oAuthService.requestAccessToken(authorizationCode);
        KakaoInfoResponse kakaoInfoResponse =oAuthService.requestOauthInfo(accessToken);
        System.err.println(kakaoInfoResponse.getKakaoAccount().getEmail());
        String email=kakaoInfoResponse.getKakaoAccount().getEmail();
        Member member=memberRepository.findByEmail(email).orElseGet(
                ()->saveMember(kakaoInfoResponse)
        );


        return OAuthLoginResponse.builder()
                .accessToken(jwtService.createAccessToken(kakaoInfoResponse.getKakaoAccount().email))
                .refreshToken(jwtService.createRefreshToken()).build();

    }

    public Member saveMember(KakaoInfoResponse kakaoInfoResponse) {
        Gender memberGender;
        Age memberAge = null;
        // gender enum으로
        if (kakaoInfoResponse.getKakaoAccount().getGender().equals("female")) {
            memberGender = Gender.WOMAN;

        } else {
            memberGender = Gender.MAN;
        }
        String age=kakaoInfoResponse.getKakaoAccount().getAge_range();

        if (age.equals("10~14") | age.equals("15~19")) {
            memberAge = Age.TEENS;
        } else if (age.equals("20~29")) {
            memberAge = Age.TWENTIES;
        } else if (age.equals("30~39")) {
            memberAge = Age.THIRTIES;
        } else if (age.equals("40~49")) {
            memberAge = Age.FORTIES;
        } else if (age.equals("50~59")) {
            memberAge = Age.FIFTIES;
        } else if (age.equals("60~69")) {
            memberAge = Age.SIXTIES;
        }

        System.err.println(kakaoInfoResponse.getKakaoAccount().getBirthDay());

        Member member = Member.builder()
                .email(kakaoInfoResponse.getKakaoAccount().getEmail())
                .gender(memberGender)
                .ageRange(memberAge)
                .role(Role.USER)
                .name(kakaoInfoResponse.getKakaoAccount().getProfile().getNickname())
                .birthDay(kakaoInfoResponse.getKakaoAccount().getBirthDay())
                .profileImg(kakaoInfoResponse.getKakaoAccount().getProfile().getProfile_image_url())
                .build();
        memberRepository.saveAndFlush(member);
        return  member;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("해당 멤버가 존재하지 않습니다"));
    }
}
