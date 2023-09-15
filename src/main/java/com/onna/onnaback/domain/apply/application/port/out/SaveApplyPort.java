package com.onna.onnaback.domain.apply.application.port.out;

import com.onna.onnaback.domain.apply.domain.AcceptStatus;
import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.domain.Spark;

public interface SaveApplyPort {
    String saveApply(Member member, Spark spark);

    String saveProcess(MemberSparkMapping memberSparkMapping, AcceptStatus acceptStatus);
}
