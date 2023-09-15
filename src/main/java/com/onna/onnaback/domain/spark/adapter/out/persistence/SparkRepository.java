package com.onna.onnaback.domain.spark.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.spark.domain.Spark;

public interface SparkRepository extends JpaRepository<Spark, Long>, JpaSpecificationExecutor<Spark> {
    Optional<Spark> findBySparkId(Long sparkId);

    @Query("select s from Spark s  where s.place.placeId = :placeId")
    Page<Spark> findSparksByPlace(@Param("placeId") Long placeId, Pageable pageable);

    List<Spark> findAllByHostMemberId(Long hostId);

    @Query("select s from Spark s join fetch s.host where s.sparkId = :id")
    Spark findSparkAndHost(@Param("id") Long id);

}
