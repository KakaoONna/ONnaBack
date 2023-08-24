package com.onna.onnaback.domain.place.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceReloadDto {
    Long id;

    Long sparkCnt; // 스파크 클래스, 미팅 개수

    Double longitude;

    Double latitude;

    @Builder
    public PlaceReloadDto(Long placeId, Double longitude, Double latitude, Long sparkCnt) {
        this.id = placeId;
        this.sparkCnt = sparkCnt;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
