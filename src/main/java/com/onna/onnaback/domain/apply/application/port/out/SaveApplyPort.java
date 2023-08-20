package com.onna.onnaback.domain.apply.application.port.out;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.domain.Spark;

public interface SaveApplyPort {
    String saveApply(Member member, Spark spark);
}
