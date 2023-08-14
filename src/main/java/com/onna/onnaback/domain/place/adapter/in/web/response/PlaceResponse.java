package com.onna.onnaback.domain.place.adapter.in.web.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.Spark;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceResponse {

    Long id;

    String name;

    List<SparkType> sparkType; // 스파크 클래스, 미팅이 있는지 판단

    List<DurationHour> durationHour; // 진행 시간 판단

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
        this.sparkType = place.getSparkList().stream().map(Spark::getType).distinct().collect(
                Collectors.toList());
        this.durationHour = place.getSparkList().stream().map(Spark::getDuration).distinct().collect(
                Collectors.toList());
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
