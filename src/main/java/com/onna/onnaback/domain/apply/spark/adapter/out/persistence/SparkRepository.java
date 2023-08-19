package com.onna.onnaback.domain.apply.spark.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onna.onnaback.domain.apply.spark.domain.Spark;

public interface SparkRepository extends JpaRepository<Spark, Long> {
    Optional<Spark> findById(Long sparkId);
}
