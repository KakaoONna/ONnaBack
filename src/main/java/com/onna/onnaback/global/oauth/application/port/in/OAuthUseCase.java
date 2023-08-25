package com.onna.onnaback.global.oauth.application.port.in;

import com.onna.onnaback.global.oauth.adapter.in.web.response.kakao.KakaoInfoResponse;
import com.onna.onnaback.global.oauth.adapter.in.web.response.kakao.KakaoLoginRequest;

public interface OAuthUseCase {
    String requestAccessToken(String authorizationCode);

    KakaoInfoResponse requestOauthInfo(String accessToken);
}
