package com.onna.onnaback.domain.apply.spark.application.port.out;

import java.util.Optional;

import com.onna.onnaback.domain.apply.spark.domain.Spark;

public interface LoadSparkPort {
    Optional<Spark> getById(Long sparkId);
}
