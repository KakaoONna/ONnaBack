package com.onna.onnaback.domain.spark.adapter.in.web.response;

import java.time.LocalDateTime;

import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.RecruitType;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SparkListDto {

    LocalDateTime createdAt;

    Long sparkId;

    Long placeId;

    String title;

    SparkType sparkType;

    LocalDateTime sparkDate;

    DurationHour durationHour;

    Long capacity;

    Long memberCount;

    RecruitType recruitType;

    String img;

    Long price;

    String placeName;

    String detailAddress;

    Double lng;

    Double lat;

}
