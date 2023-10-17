package com.onna.onnaback.domain.spark.adapter.in.web.response;

import lombok.Getter;

@Getter
public class SparkHostResponse {
    Long sparkId;

    String img;

    public SparkHostResponse(Long sparkId, String img) {
        this.sparkId = sparkId;
        this.img = img;
    }
}
