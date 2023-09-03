package com.onna.onnaback.domain.apply.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;

public interface ApplyRepository extends JpaRepository<MemberSparkMapping, Long> {
    List<MemberSparkMapping> findAllByApplicantMemberId(Long applicantId);

    List<MemberSparkMapping> findAllByApplySparkSparkId(Long sparkId);
}