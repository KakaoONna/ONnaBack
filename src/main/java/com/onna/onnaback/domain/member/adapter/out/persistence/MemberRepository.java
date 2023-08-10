package com.onna.onnaback.domain.member.adapter.out.persistence;

import com.onna.onnaback.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member,Long> {
}
