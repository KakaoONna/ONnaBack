package com.onna.onnaback.domain.place.adapter.in.web.response;

import java.time.LocalDateTime;

import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceResponse {

    Long id;

    String name;

    String img;

    String detailAddress;

    String phoneNum;

    String businessHour;

    String description;

    String detailInfo;

    PlaceType type;

    Double longitude;

    Double latitude;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @Builder
    public PlaceResponse(Place place) {
        this.id = place.getPlaceId();
        this.name = place.getName();
        this.img = place.getImg();
        this.detailAddress = place.getDetailAddress();
        this.phoneNum = place.getPhoneNum();
        this.businessHour = place.getBusinessHour();
        this.description = place.getDescription();
        this.detailInfo = place.getDetailInfo();
        this.type = place.getType();
        this.longitude = place.getLongitude();
        this.latitude = place.getLatitude();
        this.createdAt = place.getCreatedAt();
        this.updatedAt = place.getUpdatedAt();
    }

}
