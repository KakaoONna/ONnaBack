package com.onna.onnaback.domain.place.adapter.in.web.response;

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

}
