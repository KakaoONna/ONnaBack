package com.onna.onnaback.domain.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.member.application.port.in.MemberUseCase;
import com.onna.onnaback.domain.member.application.port.out.LoadMemberPort;
import com.onna.onnaback.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements MemberUseCase {

    private final LoadMemberPort loadMemberPort;

    /**
     * 회원 정보를 반환합니다.
     * @param memberId
     * @return 회원 정보
     */
    @Override
    public Member getById(Long memberId) {
        return loadMemberPort.getById(memberId).orElseThrow();
    }
}
