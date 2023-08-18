package com.onna.onnaback.domain.apply.adapter.out.persistence;

import static com.onna.onnaback.domain.apply.domain.AcceptStatus.PENDING;

import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.apply.application.port.out.SaveApplyPort;
import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.apply.spark.domain.Spark;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ApplyPersistenceAdapter implements SaveApplyPort {

    private final ApplyRepository applyRepository;

    @Override
    public String saveApply(Member member, Spark spark) {
        MemberSparkMapping memberSparkMapping = MemberSparkMapping.builder()
                                                                  .applicant(member)
                                                                  .applySpark(spark)
                                                                  .acceptStatus(PENDING)
                                                                  .build();
        applyRepository.save(memberSparkMapping);
        return "apply success";
    }
}
