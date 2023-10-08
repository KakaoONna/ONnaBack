package com.onna.onnaback.domain.apply.adapter.in.web.response;

import java.time.LocalDateTime;

import com.onna.onnaback.domain.apply.domain.AcceptStatus;
import com.onna.onnaback.domain.spark.domain.CapacityType;
import com.onna.onnaback.domain.spark.domain.DurationHour;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplyDto {

    private Long sparkId;

    private String placeName;

    private LocalDateTime sparkDate;

    private DurationHour durationHour;

    private Long memberCount;

    private Long capacity;

    private CapacityType capacityType;

    private Long price;

    private String img;

    private String title;

    private AcceptStatus acceptStatus;
}