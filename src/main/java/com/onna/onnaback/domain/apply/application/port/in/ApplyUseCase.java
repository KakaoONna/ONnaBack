package com.onna.onnaback.domain.apply.application.port.in;

public interface ApplyUseCase {
    String apply(
            Long memberId,
            Long sparkId
    );
}
