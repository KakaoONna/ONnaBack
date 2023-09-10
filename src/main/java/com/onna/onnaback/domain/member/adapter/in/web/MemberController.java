package com.onna.onnaback.domain.member.adapter.in.web;

import com.onna.onnaback.domain.member.adapter.in.web.response.MemberInfoResponse;
import com.onna.onnaback.domain.member.application.port.in.MemberUseCase;

import com.onna.onnaback.domain.member.application.service.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberUseCase memberUseCase;

    @Operation(description = "유저정보 조회")
    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return ResponseEntity.ok().body(memberUseCase.getMemberInfo(customUserDetails.getMember()));
    }

}
