package com.onna.onnaback.global.oauth.adapter.in.web;

import com.onna.onnaback.global.oauth.adapter.in.web.response.kakao.KakaoLoginRequest;
import com.onna.onnaback.global.oauth.adapter.in.web.response.OAuthLoginResponse;
import com.onna.onnaback.global.oauth.application.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @Operation(description = "카카오 로그인")
    @PostMapping("")
    public ResponseEntity<OAuthLoginResponse> loginKakao(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
        System.err.println(kakaoLoginRequest.getAuthorizationCode());
        return ResponseEntity.ok(oAuthLoginService.login(kakaoLoginRequest.getAuthorizationCode()));
    }


}
