package com.onna.onnaback.domain.spark.adapter.in.web.response;

import java.time.LocalDateTime;
import java.util.List;

import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.RecruitType;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@AllArgsConstructor
public class SparkResponse {

    long sparkId;

    long placeId;

    String title;

    SparkType sparkType;

    LocalDateTime sparkDate;

    DurationHour durationHour;

    Long capacity;

    Long memberCount;

    RecruitType recruitType;

    Long price;

    String hostName;

    String hostImg;

    String hostDetail;

    String description;

    List<ParticipateMemberDto> participateMember;

    String detailAddress;

    Double lng;

    Double lat;

}
