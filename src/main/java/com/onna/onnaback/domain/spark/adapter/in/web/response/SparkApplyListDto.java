package com.onna.onnaback.domain.spark.adapter.in.web.response;

import com.onna.onnaback.domain.apply.domain.AcceptStatus;
import com.onna.onnaback.domain.member.domain.Age;
import com.onna.onnaback.domain.member.domain.Gender;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SparkApplyListDto {
    Long applicantId;

    String applicantImg;

    String applicantName;

    Age ageRange;

    Gender gender;

    AcceptStatus acceptStatus;

    @Builder
    public SparkApplyListDto(Long applicantId, String applicantImg, String applicantName, Age ageRange,
                             Gender gender, AcceptStatus acceptStatus) {
        this.applicantId = applicantId;
        this.applicantImg = applicantImg;
        this.applicantName = applicantName;
        this.ageRange = ageRange;
        this.gender = gender;
        this.acceptStatus = acceptStatus;
    }
}
