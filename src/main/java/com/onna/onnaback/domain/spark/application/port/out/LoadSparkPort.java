package com.onna.onnaback.domain.spark.application.port.out;


import java.util.List;
import java.util.Optional;

import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.domain.Spark;
import org.springframework.data.domain.Pageable;

public interface LoadSparkPort {
    Optional<Spark> getById(Long sparkId);
    List<SparkResponse> getSparkListByPlaceId(Pageable pageable, Long placeId);
}
