package com.onna.onnaback.domain.place.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceReloadDto;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SparkType;

public interface PlaceUseCase {
    List<PlaceReloadDto> reload(
            SparkType sparkType, DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    Place getById(Long placeId);
}
