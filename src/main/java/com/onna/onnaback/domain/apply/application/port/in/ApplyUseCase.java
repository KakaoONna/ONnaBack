package com.onna.onnaback.domain.apply.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;

public interface ApplyUseCase {
    String apply(
            Long memberId,
            Long sparkId
    );

    List<ApplyDto> getList(Long memberId);
}