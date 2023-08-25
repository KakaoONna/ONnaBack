package com.onna.onnaback.domain.spark.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SortType;
import com.onna.onnaback.domain.spark.domain.Spark;

public interface SparkUseCase {
    Spark getById(Long sparkId);

    String uploadSpark(HostDto hostDto);

    List<SparkResponse> getList(
            int page,
            DurationHour durationHour, SortType sortType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    List<SparkResponse> getSparkListByPlaceId(int page, int size, Long placeId);
}
