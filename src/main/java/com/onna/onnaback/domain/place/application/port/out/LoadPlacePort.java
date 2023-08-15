package com.onna.onnaback.domain.place.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onna.onnaback.domain.place.domain.Place;

public interface LoadPlacePort {
    List<Place> getList(
            Pageable pageable,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );
}
