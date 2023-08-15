package com.onna.onnaback.domain.place.application.port.in;

import java.util.List;

import com.onna.onnaback.domain.place.domain.Place;

public interface PlaceUseCase {
    List<Place> reload(
            int page,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );
}
