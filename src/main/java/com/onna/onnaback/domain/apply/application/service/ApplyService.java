package com.onna.onnaback.domain.apply.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;
import com.onna.onnaback.domain.apply.application.port.in.ApplyUseCase;
import com.onna.onnaback.domain.apply.application.port.out.LoadApplyPort;
import com.onna.onnaback.domain.apply.application.port.out.SaveApplyPort;
import com.onna.onnaback.domain.apply.domain.AcceptStatus;
import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.member.application.port.in.MemberUseCase;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.global.exception.BaseException;
import com.onna.onnaback.global.exception.ErrorCode;

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
    public String apply(Member applicant, Long sparkId) throws BaseException {
        Member member = memberUseCase.getById(applicant.getMemberId());
        Spark spark = sparkUseCase.getById(sparkId);
        if (loadApplyPort.isAlreadyApply(applicant.getMemberId(), sparkId)) {
            throw new BaseException(ErrorCode.APPLY_ALREADY);
        }
        if (spark.getMemberCount() >= spark.getCapacity()) {
            throw new BaseException(ErrorCode.OVER_MEMBERCOUNT);
        }

        return saveApplyPort.saveApply(member, spark);
    }

    @Override
    public List<ApplyDto> getList(Member applicant) {
        return loadApplyPort.getList(applicant);
    }

    @Override
    @Transactional
    public String applyProcess(Long sparkId, Long applicantId, AcceptStatus acceptStatus) {
        MemberSparkMapping memberSparkMapping = loadApplyPort.getApply(applicantId, sparkId);
        // 인원 추가 검사
        if (memberSparkMapping.getApplySpark().getMemberCount() >= memberSparkMapping.getApplySpark()
                                                                                     .getCapacity()) {
            throw new BaseException(ErrorCode.OVER_MEMBERCOUNT);
        }
        if (acceptStatus == AcceptStatus.ACCEPT) {
            sparkUseCase.increaseMemberCount(memberSparkMapping.getApplySpark());
        }

        return saveApplyPort.saveProcess(memberSparkMapping, acceptStatus);
    }
}
