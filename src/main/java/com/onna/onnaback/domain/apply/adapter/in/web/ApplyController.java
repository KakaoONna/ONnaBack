package com.onna.onnaback.domain.apply.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onna.onnaback.domain.apply.adapter.in.web.request.ApplyRequest;
import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;
import com.onna.onnaback.domain.apply.application.port.in.ApplyUseCase;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.global.oauth.application.service.CustomUserDetails;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/apply")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyUseCase applyUseCase;

    @Operation(description = "스파크 클래스/미팅 지원하기")
    @PostMapping("/spark")
    public ResponseEntity<String> applySpark(@RequestBody ApplyRequest applyRequest,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member applicant = customUserDetails.getMember();
        Long sparkId = applyRequest.getSparkId();
        return ResponseEntity.ok().body(applyUseCase.apply(applicant, sparkId));
    }

    @Operation(description = "신청 내역 확인하기")
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<ApplyDto>> getList(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok().body(applyUseCase.getList(memberId));
    }

}
