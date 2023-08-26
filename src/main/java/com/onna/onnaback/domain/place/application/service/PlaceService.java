package com.onna.onnaback.domain.place.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceReloadDto;
import com.onna.onnaback.domain.place.application.port.in.PlaceUseCase;
import com.onna.onnaback.domain.place.application.port.out.LoadPlacePort;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService implements PlaceUseCase {
    private final LoadPlacePort loadPlacePort;

    @Override
    public List<PlaceReloadDto> reload(
            SparkType sparkType, DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    ) {
        // todo: 부산 외곽의 경우 에러 처리

        // 스파크 클래스/미팅 카운트 가져오기
        return loadPlacePort.getMarkers(
                sparkType, durationHour, placeType,
                southwestLongitude, northeastLongitude,
                southwestLatitude, northeastLatitude);
    }

    @Override
    public Place getById(Long placeId) {
        return loadPlacePort.getById(placeId).orElseThrow();
    }
}
