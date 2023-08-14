package com.onna.onnaback.domain.member.adapter.out.persistence;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.member.domain.SocialType;
import com.onna.onnaback.global.oauth.OAuthAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member,Long> {

    public Optional<Member> findByName(String username);

    public Optional<Member> findByEmail(String email);

    public Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

    public Optional<Member> findByRefreshToken(String refreshToken);
}
