package com.onna.onnaback.domain.apply.spark.adater.in.web.request;

import com.onna.onnaback.domain.apply.spark.domain.DurationHour;
import com.onna.onnaback.domain.apply.spark.domain.SparkType;

import lombok.Getter;

@Getter
public class HostDto {

    private Long hostId;

    private Long placeId;

    private String title;

    private String description;

    private SparkType type;

    private String sparkDate;

    private Long price;

    private Long capacity;

    private DurationHour durationHour;

    private String hostDetail;
}
