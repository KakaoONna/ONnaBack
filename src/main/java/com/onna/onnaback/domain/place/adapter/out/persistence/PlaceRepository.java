package com.onna.onnaback.domain.place.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onna.onnaback.domain.place.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByLongitudeBetweenAndLatitudeBetween(Double southwestLongitude,
                                                            Double northeastLongitude,
                                                            Double southwestLatitude,
                                                            Double northeastLatitude);
}
