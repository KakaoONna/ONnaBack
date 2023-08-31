package com.onna.onnaback.domain.spark.application.port.out;

import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.domain.Place;

public interface SaveSparkPort {
    String saveApply(Member host, Place place, HostDto hostDto);
}