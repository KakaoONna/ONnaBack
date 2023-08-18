package com.onna.onnaback.domain.member.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.member.application.port.out.LoadMemberPort;
import com.onna.onnaback.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> getById(Long memberId) {
        // todo: orElseThrow 추가
        return memberRepository.findById(memberId);
    }
}
