package com.onna.onnaback.domain.place.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.apply.spark.domain.DurationHour;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;

public interface PlaceUseCase {
    List<Place> reload(
            int page,
            DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    Place getById(Long placeId);
}
