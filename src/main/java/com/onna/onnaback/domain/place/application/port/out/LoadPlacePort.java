package com.onna.onnaback.domain.place.application.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceReloadDto;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.DurationHour;

public interface LoadPlacePort {

    List<PlaceReloadDto> getMarkers(
            DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    List<Place> getList(
            Pageable pageable,
            DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    Optional<Place> getById(Long placeId);
}
