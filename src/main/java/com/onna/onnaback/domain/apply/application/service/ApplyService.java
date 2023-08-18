package com.onna.onnaback.domain.apply.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.apply.application.port.in.ApplyUseCase;
import com.onna.onnaback.domain.apply.application.port.out.SaveApplyPort;
import com.onna.onnaback.domain.member.application.port.in.MemberUseCase;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.apply.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.apply.spark.domain.Spark;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService implements ApplyUseCase {

    private final MemberUseCase memberUseCase;
    private final SparkUseCase sparkUseCase;
    private final SaveApplyPort saveApplyPort;

    @Override
    @Transactional
    public String apply(Long memberId, Long sparkId) {
        Member member = memberUseCase.getById(memberId);
        Spark spark = sparkUseCase.getById(sparkId);
        return saveApplyPort.saveApply(member, spark);
    }
}
