package com.onna.onnaback.domain.member.application.port.in;

import com.onna.onnaback.domain.member.domain.Member;

public interface MemberUseCase {
    Member getById(Long memberId);
}
