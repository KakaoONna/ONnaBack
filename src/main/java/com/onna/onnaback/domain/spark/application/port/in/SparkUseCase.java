package com.onna.onnaback.domain.spark.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.HostListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkApplyListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SortType;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.domain.spark.domain.SparkType;

public interface SparkUseCase {
    Spark getById(Long sparkId);

    Spark uploadSpark(Member host, HostDto hostDto);

    List<SparkListDto> getList(
            int page,
            SparkType sparkType,
            DurationHour durationHour, SortType sortType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    List<HostListDto> getHostList(Member host);

    List<SparkApplyListDto> getSparkApplyList(Long sparkId);

    List<SparkResponse> getSparkListByPlaceId(int page, int size, Long placeId);

    SparkResponse getSparkInfo(Long id);

    Spark increaseMemberCount(Spark spark);

    List<SparkResponse> searchSpark(String value);
}