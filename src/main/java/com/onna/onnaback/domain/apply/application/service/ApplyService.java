package com.onna.onnaback.domain.apply.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;
import com.onna.onnaback.domain.apply.application.port.in.ApplyUseCase;
import com.onna.onnaback.domain.apply.application.port.out.LoadApplyPort;
import com.onna.onnaback.domain.apply.application.port.out.SaveApplyPort;
import com.onna.onnaback.domain.member.application.port.in.MemberUseCase;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.spark.domain.Spark;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService implements ApplyUseCase {

    private final MemberUseCase memberUseCase;
    private final SparkUseCase sparkUseCase;
    private final SaveApplyPort saveApplyPort;
    private final LoadApplyPort loadApplyPort;

    @Override
    @Transactional
    public String apply(Long memberId, Long sparkId) {
        Member member = memberUseCase.getById(memberId);
        Spark spark = sparkUseCase.getById(sparkId);
        return saveApplyPort.saveApply(member, spark);
    }

    @Override
    public List<ApplyDto> getList(Long memberId) {
        Member applicant = memberUseCase.getById(memberId);
        return loadApplyPort.getList(applicant);
    }
}
