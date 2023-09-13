package com.onna.onnaback.domain.spark.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.adapter.in.web.response.HostListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SortType;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.domain.spark.domain.SparkType;

public interface LoadSparkPort {
    Spark getById(Long sparkId);

    List<SparkResponse> getSparkListByPlaceId(Pageable pageable, Long placeId);

    List<SparkListDto> getList(
            Pageable pageable, SparkType sparkType,
            DurationHour durationHour, SortType sortType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    List<HostListDto> getHostList(Member host);

    SparkResponse getSparkInfo(Long id);

}
