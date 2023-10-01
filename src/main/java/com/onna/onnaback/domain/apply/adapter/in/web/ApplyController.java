package com.onna.onnaback.domain.apply.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onna.onnaback.domain.apply.adapter.in.web.request.ApplyProcessRequest;
import com.onna.onnaback.domain.apply.adapter.in.web.request.ApplyRequest;
import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;
import com.onna.onnaback.domain.apply.application.port.in.ApplyUseCase;
import com.onna.onnaback.domain.apply.domain.AcceptStatus;
import com.onna.onnaback.domain.member.application.service.CustomUserDetails;
import com.onna.onnaback.domain.member.domain.Member;

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
    public ResponseEntity<List<ApplyDto>> getList(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member applicant = customUserDetails.getMember();
        return ResponseEntity.ok().body(applyUseCase.getList(applicant));
    }

    @Operation(description = "스파크 지원 수락/거절하기")
    @PatchMapping("/process")
    public ResponseEntity<String> applyProcess(@RequestBody ApplyProcessRequest applyProcessRequest) {
        Long sparkId = applyProcessRequest.getSparkId();
        Long applicantId = applyProcessRequest.getApplicantId();
        AcceptStatus acceptStatus = applyProcessRequest.getAcceptStatus();
        return ResponseEntity.ok().body(applyUseCase.applyProcess(sparkId, applicantId, acceptStatus));
    }

}
