package com.onna.onnaback.domain.member.application.port.out;

import java.util.Optional;

import com.onna.onnaback.domain.member.domain.Member;

public interface LoadMemberPort {
    Optional<Member> getById(Long memberId);
}
