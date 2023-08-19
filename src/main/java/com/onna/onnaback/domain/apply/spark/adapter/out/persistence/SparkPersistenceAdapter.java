package com.onna.onnaback.domain.apply.spark.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.apply.spark.application.port.out.LoadSparkPort;
import com.onna.onnaback.domain.apply.spark.domain.Spark;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SparkPersistenceAdapter implements LoadSparkPort {

    private final SparkRepository sparkRepository;

    @Override
    public Optional<Spark> getById(Long sparkId) {
        // todo: orElseThrow 추가
        return sparkRepository.findById(sparkId);
    }
}
