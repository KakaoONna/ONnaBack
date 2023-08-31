package com.onna.onnaback.domain.member.adapter.in.web;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.global.oauth.application.service.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @GetMapping("/test")
    public ResponseEntity<Member> index(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return ResponseEntity.ok().body(Member.builder().memberId(customUserDetails.getMemberId())
                .name(customUserDetails.getUsername())
                .email(customUserDetails.getEmail()).build());
    }
}
