package com.onna.onnaback.domain.apply.adapter.out.persistence;

import static com.onna.onnaback.domain.apply.domain.AcceptStatus.PENDING;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;
import com.onna.onnaback.domain.apply.application.port.out.LoadApplyPort;
import com.onna.onnaback.domain.apply.application.port.out.SaveApplyPort;
import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.domain.Spark;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ApplyPersistenceAdapter implements SaveApplyPort, LoadApplyPort {

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

    @Override
    public List<ApplyDto> getList(Member applicant) {
        List<MemberSparkMapping> memberSparkMappings = applyRepository.findAllByApplicantMemberId(
                applicant.getMemberId());

        List<ApplyDto> applyDtos = new ArrayList<>();

        for (MemberSparkMapping memberSparkMapping : memberSparkMappings) {
            applyDtos.add(new ApplyDto(memberSparkMapping.getApplySpark().getSparkId(),
                                       memberSparkMapping.getApplySpark().getPlace().getName(),
                                       memberSparkMapping.getApplySpark().getSparkDate(),
                                       memberSparkMapping.getApplySpark().getDurationHour(),
                                       memberSparkMapping.getApplySpark().getMemberCount(),
                                       memberSparkMapping.getApplySpark().getCapacity(),
                                       memberSparkMapping.getApplySpark().getPrice(),
                                       memberSparkMapping.getApplySpark().getTitle(),
                                       memberSparkMapping.getAcceptStatus()
            ));
        }
        return applyDtos;
    }
}