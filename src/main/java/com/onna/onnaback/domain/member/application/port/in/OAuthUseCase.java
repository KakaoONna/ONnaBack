package com.onna.onnaback.domain.member.application.port.in;


import com.onna.onnaback.domain.member.adapter.out.external.response.kakao.KakaoInfoResponse;

public interface OAuthUseCase {
    String requestAccessToken(String authorizationCode);

    String requestLocalAccessToken(String authorizationCode);

    KakaoInfoResponse requestOauthInfo(String accessToken);
}
