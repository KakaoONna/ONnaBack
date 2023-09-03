package com.onna.onnaback.domain.apply.application.port.out;

import java.util.List;

import com.onna.onnaback.domain.apply.adapter.in.web.response.ApplyDto;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkApplyListDto;

public interface LoadApplyPort {
    List<ApplyDto> getList(Member applicant);

    List<SparkApplyListDto> getSparkApplyList(Long sparkId);
}