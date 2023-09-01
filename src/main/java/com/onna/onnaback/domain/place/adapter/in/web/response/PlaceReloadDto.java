package com.onna.onnaback.domain.place.adapter.in.web.response;

import com.onna.onnaback.domain.place.domain.PlaceType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceReloadDto {
    Long id;

    PlaceType placeType;

    Long sparkCnt; // 스파크 클래스, 미팅 개수

    Double longitude;

    Double latitude;

    @Builder
    public PlaceReloadDto(Long placeId, PlaceType placeType, Double longitude, Double latitude, Long sparkCnt) {
        this.id = placeId;
        this.placeType = placeType;
        this.sparkCnt = sparkCnt;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
