package com.onna.onnaback.domain.apply.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;
import com.onna.onnaback.domain.apply.domain.AcceptStatus;
import com.onna.onnaback.domain.member.domain.Member;

public interface ApplyUseCase {
    String apply(
            Member applicant,
            Long sparkId
    );

    List<ApplyDto> getList(Long memberId);

    String applyProcess(Long sparkId, Long applicantId, AcceptStatus acceptStatus);
}