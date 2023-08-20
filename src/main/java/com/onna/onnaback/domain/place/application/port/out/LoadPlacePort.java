package com.onna.onnaback.domain.place.application.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;

public interface LoadPlacePort {
    List<Place> getList(
            Pageable pageable,
            DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    Optional<Place> getById(Long placeId);
}
