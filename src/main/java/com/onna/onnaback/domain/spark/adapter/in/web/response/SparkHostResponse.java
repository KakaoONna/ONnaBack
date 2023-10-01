package com.onna.onnaback.domain.spark.adapter.in.web.response;

import lombok.Getter;

@Getter
public class SparkHostResponse {
    Long sparkId;

    public SparkHostResponse(Long sparkId) {
        this.sparkId = sparkId;
    }
}
