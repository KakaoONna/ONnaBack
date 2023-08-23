package com.onna.onnaback.domain.spark.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.spark.domain.Spark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

public interface SparkRepository extends JpaRepository<Spark, Long>, JpaSpecificationExecutor<Spark> {
    Optional<Spark> findById(Long sparkId);

    @Query("select s from Spark s where place = :place")
    Page<Spark> findSparksByPlace(@Param("place") Place place,Pageable pageable);
}
