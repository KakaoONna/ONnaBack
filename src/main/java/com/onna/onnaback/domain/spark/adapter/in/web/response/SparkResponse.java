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


    //place 관련
    private long placeId;

    private String placeDetailAddress;

    private String placeDescription;

    private Double placeLng;

    private Double placeLat;

    private String placeDetailInfo;

    private String placePhoneNum;

    private String placeBusinessHour;

    // spark 관련
    private long sparkId;

    private String sparkTitle;

    private SparkType sparkType;

    private LocalDateTime sparkDate;

    private DurationHour sparkDurationHour;

    private Long sparkCapacity;

    private Long sparkMemberCount;

    private RecruitType sparkRecruitType;

    private Long sparkPrice;

    private String sparkHostName;

    private String sparkHostImg;

    private String sparkHostDetail;

    private String sparkDescription;

    private List<ParticipateMemberDto> sparkParticipateMember;


}