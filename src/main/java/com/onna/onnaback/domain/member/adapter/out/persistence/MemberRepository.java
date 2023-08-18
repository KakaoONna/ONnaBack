package com.onna.onnaback.domain.member.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.member.domain.SocialType;

@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String username);

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

    Optional<Member> findByRefreshToken(String refreshToken);
}
