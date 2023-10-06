package com.onna.onnaback.domain.member.adapter.out.external;

import com.onna.onnaback.domain.member.adapter.out.external.response.OAuthLoginResponse;
import com.onna.onnaback.domain.member.adapter.out.external.request.KakaoLoginRequest;
import com.onna.onnaback.domain.member.application.service.OAuthLoginService;
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

    @Operation(description = "카카오 로컬 로그인")
    @PostMapping("/local")
    public ResponseEntity<OAuthLoginResponse> localLoginKakao(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
        System.err.println(kakaoLoginRequest.getAuthorizationCode());
        return ResponseEntity.ok(oAuthLoginService.localLogin(kakaoLoginRequest.getAuthorizationCode()));
    }


}
