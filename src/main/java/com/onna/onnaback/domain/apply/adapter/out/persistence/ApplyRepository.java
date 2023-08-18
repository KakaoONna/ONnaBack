package com.onna.onnaback.domain.apply.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;

public interface ApplyRepository extends JpaRepository<MemberSparkMapping, Long> {
}
