package com.onna.onnaback.domain.spark.adapter.in.web.request;

import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SparkType;
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