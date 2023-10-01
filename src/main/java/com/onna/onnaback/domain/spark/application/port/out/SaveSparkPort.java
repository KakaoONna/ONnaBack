package com.onna.onnaback.domain.spark.application.port.out;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.domain.Spark;

public interface SaveSparkPort {
    Spark saveApply(Member host, Place place, HostDto hostDto);
}