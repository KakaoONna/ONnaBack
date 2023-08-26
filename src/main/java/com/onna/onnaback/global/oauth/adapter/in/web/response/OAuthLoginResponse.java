package com.onna.onnaback.global.oauth.adapter.in.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// 유저가 우리 서비스에 로그인했을때 발급되는 토큰
public class OAuthLoginResponse {

    private String memberEmail;
    private String accessToken;
    private String refreshToken;
}
