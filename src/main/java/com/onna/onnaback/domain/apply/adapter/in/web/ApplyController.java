package com.onna.onnaback.domain.apply.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onna.onnaback.domain.apply.adapter.in.web.request.ApplyRequest;
import com.onna.onnaback.domain.apply.application.port.in.ApplyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyUseCase applyUseCase;

    @Operation(description = "스파크 클래스/미팅 지원하기")
    @PostMapping("/spark")
    public ResponseEntity<String> applySpark(@RequestBody ApplyRequest applyRequest) {
        Long memberId = applyRequest.getMemberId();
        Long sparkId = applyRequest.getSparkId();
        return ResponseEntity.ok().body(applyUseCase.apply(memberId, sparkId));
    }

}
