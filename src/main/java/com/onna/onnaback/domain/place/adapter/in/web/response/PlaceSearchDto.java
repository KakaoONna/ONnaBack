package com.onna.onnaback.domain.place.adapter.in.web.response;

import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.CapacityType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceSearchDto {

    private Long placeId;

    private String name;

    private String detailAddress;

    private PlaceType placeType;

}
