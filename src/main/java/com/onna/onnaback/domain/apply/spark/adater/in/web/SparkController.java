package com.onna.onnaback.domain.apply.spark.adater.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onna.onnaback.domain.apply.spark.adater.in.web.request.HostDto;
import com.onna.onnaback.domain.apply.spark.application.port.in.SparkUseCase;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/spark")
@RequiredArgsConstructor
public class SparkController {

    private final SparkUseCase sparkUseCase;

    @Operation(description = "주최하기")
    @PostMapping("/host")
    public ResponseEntity<String> hostSpark(@RequestBody HostDto hostDto) {
        return ResponseEntity.ok().body(sparkUseCase.uploadSpark(hostDto));
    }
}
