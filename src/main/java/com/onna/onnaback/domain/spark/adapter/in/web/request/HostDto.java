package com.onna.onnaback.domain.spark.adapter.in.web.request;

import org.springframework.web.multipart.MultipartFile;

import com.onna.onnaback.domain.spark.domain.Always;
import com.onna.onnaback.domain.spark.domain.CapacityType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HostDto {
    MultipartFile img;

    Long placeId;

    String title;

    String description;

    SparkType type;

    Always always; // 상시, 상시x

    String sparkDate; // 주최 일시

    Long price;

    CapacityType capacityType;

    Long capacity;

    DurationHour durationHour;

    String hostDetail;

    String chatUrl;
}