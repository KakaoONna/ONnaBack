package com.onna.onnaback.global.oauth.adapter.in.web.response.kakao;

import lombok.Data;


@Data
//카카오 서버에 로그인 요청
public class KakaoLoginRequest {
    private String authorizationCode;


}
