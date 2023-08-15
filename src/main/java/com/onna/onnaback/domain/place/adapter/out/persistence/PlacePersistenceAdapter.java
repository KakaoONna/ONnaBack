package com.onna.onnaback.domain.place.adapter.out.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.place.application.port.out.LoadPlacePort;
import com.onna.onnaback.domain.place.domain.Place;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PlacePersistenceAdapter implements LoadPlacePort {

    private final PlaceRepository placeRepository;

    @Override
    public List<Place> getList(Pageable pageable, Double southwestLongitude, Double northeastLongitude,
                               Double southwestLatitude, Double northeastLatitude) {
        return placeRepository.findAllByLongitudeBetweenAndLatitudeBetween(southwestLongitude,
                                                                           northeastLongitude,
                                                                           southwestLatitude,
                                                                           northeastLatitude);
    }
}
