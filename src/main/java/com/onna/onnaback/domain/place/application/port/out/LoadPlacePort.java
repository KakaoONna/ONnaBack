package com.onna.onnaback.domain.place.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.apply.spark.domain.DurationHour;

public interface LoadPlacePort {
    List<Place> getList(
            Pageable pageable,
            DurationHour durationHour, PlaceType placeType,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );
}
