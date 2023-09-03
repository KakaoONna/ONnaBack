package com.onna.onnaback.domain.spark.adapter.in.web.response;

import java.time.LocalDateTime;

import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.RecruitType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HostListDto {
    Long sparkId;

    String placeName;

    LocalDateTime sparkDate;

    DurationHour durationHour;

    Long memberCount;

    Long capacity;

    Long price;

    String title;

    RecruitType recruitType;

    @Builder
    public HostListDto(Long sparkId, String placeName, LocalDateTime sparkDate,
                       DurationHour durationHour, Long memberCount, Long capacity,
                       Long price, String title, RecruitType recruitType) {
        this.sparkId = sparkId;
        this.placeName = placeName;
        this.sparkDate = sparkDate;
        this.durationHour = durationHour;
        this.memberCount = memberCount;
        this.capacity = capacity;
        this.price = price;
        this.title = title;
        this.recruitType = recruitType;
    }
}
