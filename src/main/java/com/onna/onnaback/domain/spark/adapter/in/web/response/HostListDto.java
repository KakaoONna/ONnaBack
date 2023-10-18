package com.onna.onnaback.domain.spark.adapter.in.web.response;

import java.time.LocalDateTime;

import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.Always;
import com.onna.onnaback.domain.spark.domain.CapacityType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.RecruitType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HostListDto {
    Long sparkId;

    String placeName;

    Always always;

    LocalDateTime sparkDate;

    DurationHour durationHour;

    Long memberCount;

    CapacityType capacityType;

    Long capacity;

    Long price;

    String img;

    String title;

    RecruitType recruitType;

    PlaceType placeType;

    Long waitingCount; // 신청 대기 수

    @Builder
    public HostListDto(Long sparkId, String placeName, Always always, LocalDateTime sparkDate,
                       DurationHour durationHour, Long memberCount, CapacityType capacityType, Long capacity,
                       Long price, String img, String title, RecruitType recruitType, PlaceType placeType,
                       Long waitingCount) {
        this.sparkId = sparkId;
        this.placeName = placeName;
        this.always = always;
        this.sparkDate = sparkDate;
        this.durationHour = durationHour;
        this.memberCount = memberCount;
        this.capacityType = capacityType;
        this.capacity = capacity;
        this.price = price;
        this.img = img;
        this.title = title;
        this.recruitType = recruitType;
        this.placeType = placeType;
        this.waitingCount = waitingCount;
    }
}
