package com.onna.onnaback.domain.place.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceReloadDto;
import com.onna.onnaback.domain.place.application.port.out.LoadPlacePort;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.Spark;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PlacePersistenceAdapter implements LoadPlacePort {

    private final PlaceRepository placeRepository;

    @Override
    public List<PlaceReloadDto> getMarkers(DurationHour durationHour,
                                           PlaceType placeType,
                                           Double southwestLongitude, Double northeastLongitude,
                                           Double southwestLatitude, Double northeastLatitude) {
        Specification<Place> spec = Specification.where(null);

        if (durationHour != null) {
            spec = spec.and(hasDurationHour(durationHour));
        }

        if (placeType != null) {
            spec = spec.and(hasPlaceType(placeType));
        }

        spec = spec.and(hasLocationBetween(southwestLongitude, northeastLongitude, southwestLatitude,
                                           northeastLatitude));

        List<Place> places = placeRepository.findAll(spec);

        List<PlaceReloadDto> result = new ArrayList<>();

        for (Place place : places) {
            Long sparkCount = calculateSparkCount(place); // 메서드 호출을 통해 Spark 개수 계산
            result.add(new PlaceReloadDto(place.getPlaceId(), place.getLongitude(), place.getLatitude(),
                                          sparkCount));
        }

        return result;
    }

    private Long calculateSparkCount(Place place) {
        return (long) place.getSparkList().size();
    }

    @Override
    public List<Place> getList(Pageable pageable, DurationHour durationHour, PlaceType placeType,
                               Double southwestLongitude, Double northeastLongitude,
                               Double southwestLatitude, Double northeastLatitude) {
        Specification<Place> spec = Specification.where(null);

        if (durationHour != null) {
            spec = spec.and(hasDurationHour(durationHour));
        }

        if (placeType != null) {
            spec = spec.and(hasPlaceType(placeType));
        }

        spec = spec.and(hasLocationBetween(southwestLongitude, northeastLongitude, southwestLatitude,
                                           northeastLatitude));

        return placeRepository.findAll(spec, pageable).getContent();
    }

    @Override
    public Optional<Place> getById(Long placeId) {
        // todo: orElseThrow 추가
        return placeRepository.findById(placeId);
    }

    private Specification<Place> hasDurationHour(DurationHour durationHour) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Place, Spark> sparkJoin = root.join("sparkList");

            // durationHour 일치할 경우 추가
            predicates.add(criteriaBuilder.equal(sparkJoin.get("durationHour"), durationHour));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Place> hasPlaceType(PlaceType placeType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("placeType"), placeType);
    }

    private Specification<Place> hasLocationBetween(Double southwestLongitude, Double northeastLongitude,
                                                    Double southwestLatitude, Double northeastLatitude) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(
                    criteriaBuilder.between(root.get("longitude"), southwestLongitude, northeastLongitude));
            predicates.add(criteriaBuilder.between(root.get("latitude"), southwestLatitude, northeastLatitude));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
