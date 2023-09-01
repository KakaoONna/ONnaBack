package com.onna.onnaback.domain.place.application.port.out;

import java.util.List;
import java.util.Optional;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceReloadDto;
import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceReloadDto;
import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceSearchDto;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SparkType;

public interface LoadPlacePort {

    List<PlaceReloadDto> getMarkers(
            SparkType sparkType, DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    Optional<Place> getById(Long placeId);

    List<PlaceSearchDto> searchPlaceList(String value);

}
