package com.onna.onnaback.domain.spark.adapter.in.web.response;

import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.RecruitType;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SparkResponse {

    private long sparkId;

    private long placeId;

    private String title;

    private SparkType sparkType;

    private LocalDateTime sparkDate;

    private DurationHour durationHour;

    private Long capacity;

    private Long memberCount;

    private RecruitType recruitType;

    private Long price;

    private String hostName;

    private String hostImg;

    private String hostDetail;

    private String description;

    private List<ParticipateMemberDto> participateMember;

    private String detailAddress;

    private Double lng;

    private Double lat;

}
