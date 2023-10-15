package com.onna.onnaback.domain.place.adapter.in.web.response;

import com.onna.onnaback.domain.place.domain.PlaceType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceDetailInfo {

    private Long id;

    private String name;

    private String img;

    private String detailAddress;

    private String phoneNum;

    private String businessHour;

    private String description;

    private String detailInfo;

    private PlaceType placeType;

}
