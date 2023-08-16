package com.onna.onnaback.domain.place.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.DurationHour;

public interface PlaceUseCase {
    List<Place> reload(
            int page,
            DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );
}
