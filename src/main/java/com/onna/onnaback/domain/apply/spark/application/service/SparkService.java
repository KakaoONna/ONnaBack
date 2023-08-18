package com.onna.onnaback.domain.apply.spark.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.apply.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.apply.spark.application.port.out.LoadSparkPort;
import com.onna.onnaback.domain.apply.spark.domain.Spark;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SparkService implements SparkUseCase {

    private final LoadSparkPort loadSparkPort;

    /**
     * 스파크 미팅/클래스 정보를 반환합니다.
     * @param sparkId 스파크 아이디
     * @return 스파크 미팅/클래스 정보
     */
    @Override
    public Spark getById(Long sparkId) {
        return loadSparkPort.getById(sparkId).orElseThrow();
    }
}
