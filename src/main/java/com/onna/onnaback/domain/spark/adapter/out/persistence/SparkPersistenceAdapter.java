package com.onna.onnaback.domain.spark.adapter.out.persistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.adapter.out.persistence.PlaceRepository;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.ParticipateMemberDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.application.port.out.LoadSparkPort;
import com.onna.onnaback.domain.spark.application.port.out.SaveSparkPort;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.RecruitType;
import com.onna.onnaback.domain.spark.domain.SortType;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SparkPersistenceAdapter implements LoadSparkPort, SaveSparkPort {

    private final SparkRepository sparkRepository;
    private final PlaceRepository placeRepository;

    @Override
    public Optional<Spark> getById(Long sparkId) {
        // todo: orElseThrow 추가
        return sparkRepository.findById(sparkId);
    }

    @Override
    public List<SparkResponse> getSparkListByPlaceId(Pageable pageable, Long placeId) {
        Place place = placeRepository.findByPlaceId(placeId);
        System.err.println(place.getName());
        return sparkRepository.findSparksByPlace(place, pageable).getContent().stream().map(spark ->
                                                                                                    SparkResponse.builder()
                                                                                                                 .sparkId(
                                                                                                                         spark.getSparkId())
                                                                                                                 .description(
                                                                                                                         spark.getDescription())
                                                                                                                 .placeId(
                                                                                                                         spark.getPlace()
                                                                                                                              .getPlaceId())
                                                                                                                 .detailAddress(
                                                                                                                         spark.getPlace()
                                                                                                                              .getDetailAddress())
                                                                                                                 .lat(spark.getPlace()
                                                                                                                           .getLatitude())
                                                                                                                 .lng(spark.getPlace()
                                                                                                                           .getLongitude())
                                                                                                                 .sparkDate(
                                                                                                                         spark.getSparkDate())
                                                                                                                 .price(spark.getPrice())
                                                                                                                 .sparkType(
                                                                                                                         spark.getType())
                                                                                                                 .hostImg(
                                                                                                                         spark.getHost()
                                                                                                                              .getProfileImg())
                                                                                                                 .title(spark.getTitle())
                                                                                                                 .hostName(
                                                                                                                         spark.getHost()
                                                                                                                              .getName())
                                                                                                                 .recruitType(
                                                                                                                         spark.getRecruitType())
                                                                                                                 .participateMember(
                                                                                                                         spark.getMemberSparkMappingList()
                                                                                                                              .stream()
                                                                                                                              .map(
                                                                                                                                      memberSparkMapping -> ParticipateMemberDto.builder()
                                                                                                                                                                                .memberId(
                                                                                                                                                                                        memberSparkMapping.getApplicant()
                                                                                                                                                                                                          .getMemberId())
                                                                                                                                                                                .profileImg(
                                                                                                                                                                                        memberSparkMapping.getApplicant()
                                                                                                                                                                                                          .getProfileImg())
                                                                                                                                                                                .build()
                                                                                                                              )
                                                                                                                              .collect(
                                                                                                                                      Collectors.toList()))
                                                                                                                 .durationHour(
                                                                                                                         spark.getDurationHour())
                                                                                                                 .hostDetail(
                                                                                                                         spark.getHostDetail())
                                                                                                                 .memberCount(
                                                                                                                         spark.getMemberCount())
                                                                                                                 .capacity(
                                                                                                                         spark.getCapacity())
                                                                                                                 .build())
                              .collect(Collectors.toList());
    }

    @Override
    public List<SparkListDto> getList(Pageable pageable, SparkType sparkType,
                                      DurationHour durationHour, SortType sortType,
                                      Double southwestLongitude, Double northeastLongitude,
                                      Double southwestLatitude, Double northeastLatitude) {
        Specification<Spark> spec = Specification.where(null);

        if (sparkType != null) {
            spec = spec.and(hasSparkType(sparkType));
        }

        if (durationHour != null) {
            spec = spec.and(hasDurationHour(durationHour));
        }

        // 모집중
        if (sortType == SortType.RECRUITING) {
            spec = spec.and(hasRecruiting());
        }

        spec = spec.and(hasLocationBetween(southwestLongitude, northeastLongitude, southwestLatitude,
                                           northeastLatitude));

        List<Spark> sparks = sparkRepository.findAll(spec, pageable).getContent();

        Set<Long> uniqueSparkIds = new HashSet<>(); // 중복을 제거하기 위한 Set

        List<SparkListDto> result = new ArrayList<>();

        for (Spark spark : sparks) {
            if (!uniqueSparkIds.contains(spark.getSparkId())) {
                result.add(new SparkListDto(
                        spark.getCreatedAt(),
                        spark.getSparkId(),
                        spark.getPlace().getPlaceId(),
                        spark.getTitle(),
                        spark.getType(),
                        spark.getSparkDate(),
                        spark.getDurationHour(),
                        spark.getCapacity(),
                        spark.getMemberCount(),
                        spark.getRecruitType(),
                        spark.getPlace().getImg(),
                        spark.getPrice(),
                        spark.getPlace().getDetailAddress(),
                        spark.getPlace().getLongitude(),
                        spark.getPlace().getLatitude()
                ));
                uniqueSparkIds.add(spark.getSparkId());
            }
        }
        // 최신순인경우 소팅
        if (sortType == SortType.NEW) {
            result.sort(Comparator.comparing(SparkListDto::getCreatedAt).reversed());
        }

        return result;
    }

    @Override
    public String saveApply(Member host, Place place, HostDto hostDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(hostDto.getSparkDate(), formatter);
        Spark spark = Spark.builder()
                           .title(hostDto.getTitle())
                           .description(hostDto.getDescription())
                           .type(hostDto.getType())
                           .sparkDate(localDateTime)
                           .price(hostDto.getPrice())
                           .memberCount(0L)
                           .capacity(hostDto.getCapacity())
                           .durationHour(hostDto.getDurationHour())
                           .hostDetail(hostDto.getHostDetail())
                           .host(host)
                           .place(place)
                           .build();
        sparkRepository.save(spark);
        return "host success";
    }

    private Specification<Spark> equalPlace(Long placeId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("placeId"), placeId);
    }

    private Specification<Spark> hasSparkType(SparkType sparkType) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("type"), sparkType));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Spark> hasDurationHour(DurationHour durationHour) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("durationHour"), durationHour));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Spark> hasRecruiting() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("recruitType"), RecruitType.RECRUITING));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Spark> hasLocationBetween(Double southwestLongitude, Double northeastLongitude,
                                                    Double southwestLatitude, Double northeastLatitude) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Spark, Place> sparkJoin = root.join("place");

            predicates.add(
                    criteriaBuilder.between(sparkJoin.get("longitude"), southwestLongitude,
                                            northeastLongitude));

            predicates.add(
                    criteriaBuilder.between(sparkJoin.get("latitude"), southwestLatitude, northeastLatitude));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
