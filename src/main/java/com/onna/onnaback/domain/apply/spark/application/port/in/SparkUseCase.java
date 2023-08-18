package com.onna.onnaback.domain.apply.spark.application.port.in;

import com.onna.onnaback.domain.apply.spark.adater.in.web.request.HostDto;
import com.onna.onnaback.domain.apply.spark.domain.Spark;

public interface SparkUseCase {
    Spark getById(Long sparkId);

    String uploadSpark(HostDto hostDto);
}
