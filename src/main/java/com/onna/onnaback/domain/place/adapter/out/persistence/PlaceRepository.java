package com.onna.onnaback.domain.place.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.onna.onnaback.domain.place.domain.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {
    Optional<Place> findById(Long memberId);

    @Query("select p from Place p where p.placeId = :placeId")
    Place findByPlaceId(@Param("placeId") Long placeId);

    List<Place> findByNameContaining(String name);

}
