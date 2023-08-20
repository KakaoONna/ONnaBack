package com.onna.onnaback.domain.spark.application.port.in;

import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.domain.Spark;

import java.util.List;

public interface SparkUseCase {
    Spark getById(Long sparkId);

    String uploadSpark(HostDto hostDto);

    List<SparkResponse> getSparkListByPlaceId(int page, int size, Long placeId);
}
