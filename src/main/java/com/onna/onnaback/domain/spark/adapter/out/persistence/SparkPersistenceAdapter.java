package com.onna.onnaback.domain.spark.adapter.out.persistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.apply.domain.AcceptStatus;
import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.adapter.out.persistence.PlaceRepository;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.HostListDto;
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
import com.onna.onnaback.global.exception.BaseException;
import com.onna.onnaback.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SparkPersistenceAdapter implements LoadSparkPort, SaveSparkPort {

    private final SparkRepository sparkRepository;
    private final PlaceRepository placeRepository;

    @Override
    public Spark getById(Long sparkId) {
        return sparkRepository.findBySparkId(sparkId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
    }

    @Override
    public List<SparkResponse> getSparkListByPlaceId(Pageable pageable, Long placeId) {
        return sparkRepository.findSparksByPlace(placeId, pageable).getContent()
                              .stream().map(spark -> SparkResponse.builder()
                                                                  .sparkId(spark.getSparkId())
                                                                  .img(spark.getImg())
                                                                  .title(spark.getTitle())
                                                                  .durationHour(spark.getDurationHour())
                                                                  .sparkType(spark.getType())
                                                                  .sparkDate(spark.getSparkDate())
                                                                  .capacity(spark.getCapacity())
                                                                  .memberCount(
                                                                          (long) spark.getMemberSparkMappingList()
                                                                                      .size())
                                                                  .price(spark.getPrice())
                                                                  .hostName(spark.getHost().getName())
                                                                  .hostDetail(spark.getHostDetail())
                                                                  .hostImg(spark.getHost().getProfileImg())
                                                                  .description(spark.getDescription())
                                                                  .participateMember(
                                                                          spark.getMemberSparkMappingList()
                                                                               .stream().map(
                                                                                       memberSparkMapping -> ParticipateMemberDto.builder()
                                                                                                                                 .memberId(
                                                                                                                                         memberSparkMapping.getApplicant()
                                                                                                                                                           .getMemberId())
                                                                                                                                 .profileImg(
                                                                                                                                         memberSparkMapping.getApplicant()
                                                                                                                                                           .getProfileImg())
                                                                                                                                 .build()
                                                                               ).collect(Collectors.toList()))
                                                                  .build()).collect(Collectors.toList());
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
                        spark.getPlace().getName(),
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
    public List<HostListDto> getHostList(Member host) {
        List<Spark> sparks = sparkRepository.findAllByHostMemberId(host.getMemberId());

        List<HostListDto> hostListDtos = new ArrayList<>();

        for (Spark spark : sparks) {
            // 신청 대기 수 카운트
            List<MemberSparkMapping> memberSparkMapping = spark.getMemberSparkMappingList();
            Long waitingCount = memberSparkMapping.stream()
                                                  .filter(mapping -> mapping.getAcceptStatus()
                                                                     == AcceptStatus.PENDING)
                                                  .count();

            hostListDtos.add(new HostListDto(
                    spark.getSparkId(),
                    spark.getPlace().getName(),
                    spark.getAlways(),
                    spark.getSparkDate(),
                    spark.getDurationHour(),
                    spark.getMemberCount(),
                    spark.getCapacityType(),
                    spark.getCapacity(),
                    spark.getPrice(),
                    spark.getImg(),
                    spark.getTitle(),
                    spark.getRecruitType(),
                    waitingCount
            ));
        }

        return hostListDtos;
    }

    @Override
    public SparkResponse getSparkInfo(Long id) {
        Spark spark = sparkRepository.findSparkAndHost(id);

        return SparkResponse.builder()
                            .sparkId(spark.getSparkId())
                            .title(spark.getTitle())
                            .img(spark.getImg())
                            .sparkType(spark.getType())
                            .sparkDate(spark.getSparkDate())
                            .price(spark.getPrice())
                            .durationHour(spark.getDurationHour())
                            .capacity(spark.getCapacity())
                            .hostName(spark.getHost().getName())
                            .hostImg(spark.getHost().getProfileImg())
                            .hostDetail(spark.getHostDetail())
                            .memberCount(spark.getMemberCount())
                            .recruitType(spark.getRecruitType())
                            .placeId(spark.getPlace().getPlaceId())
                            .placeName(spark.getPlace().getName())
                            .lng(spark.getPlace().getLongitude())
                            .lat(spark.getPlace().getLatitude())
                            .detailAddress(spark.getPlace().getDetailAddress())
                            .participateMember(
                                    spark.getMemberSparkMappingList()
                                         .stream()
                                         .filter(memberSparkMapping -> memberSparkMapping.getAcceptStatus()
                                                                       == AcceptStatus.ACCEPT)
                                         .map(
                                                 memberSparkMapping -> ParticipateMemberDto.builder()
                                                                                           .memberId(
                                                                                                   memberSparkMapping.getApplicant()
                                                                                                                     .getMemberId())
                                                                                           .profileImg(
                                                                                                   memberSparkMapping.getApplicant()
                                                                                                                     .getProfileImg())
                                                                                           .build()
                                         ).collect(Collectors.toList()))
                            .build();
    }

    @Override
    public List<SparkResponse> searchSpark(String value) {
        return sparkRepository.findByTitleContaining(value).stream().map(
                spark -> SparkResponse.builder()
                                      .sparkId(spark.getSparkId())
                                      .sparkDate(spark.getSparkDate())
                                      .sparkType(spark.getType())
                                      .memberCount(spark.getMemberCount())
                                      .hostImg(spark.getHost().getProfileImg())
                                      .img(spark.getImg())
                                      .durationHour(spark.getDurationHour())
                                      .detailAddress(spark.getPlace().getDetailAddress())
                                      .hostName(spark.getHost().getName())
                                      .hostDetail(spark.getHostDetail())
                                      .recruitType(spark.getRecruitType())
                                      .capacity(spark.getCapacity())
                                      .title(spark.getTitle())
                                      .price(spark.getPrice())
                                      .description(spark.getDescription())
                                      .lat(spark.getPlace().getLatitude())
                                      .lng(spark.getPlace().getLongitude())
                                      .participateMember(
                                              spark.getMemberSparkMappingList()
                                                   .stream().map(
                                                           memberSparkMapping -> ParticipateMemberDto.builder()
                                                                                                     .memberId(
                                                                                                             memberSparkMapping.getApplicant()
                                                                                                                               .getMemberId())
                                                                                                     .profileImg(
                                                                                                             memberSparkMapping.getApplicant()
                                                                                                                               .getProfileImg())
                                                                                                     .build()
                                                   ).collect(Collectors.toList()))
                                      .placeId(spark.getPlace().getPlaceId())
                                      .placeName(spark.getPlace().getName())
                                      .img(spark.getImg())
                                      .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Spark saveApply(Member host, Place place, HostDto hostDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(hostDto.getSparkDate(), formatter);
        Spark spark = Spark.builder()
                           .title(hostDto.getTitle())
                           .description(hostDto.getDescription())
                           .type(hostDto.getType())
                           .always(hostDto.getAlways())
                           .sparkDate(localDateTime)
                           .price(hostDto.getPrice())
                           .memberCount(0L)
                           .capacityType(hostDto.getCapacityType())
                           .capacity(hostDto.getCapacity())
                           .durationHour(hostDto.getDurationHour())
                           .hostDetail(hostDto.getHostDetail())
                           .recruitType(RecruitType.RECRUITING)
                           .host(host)
                           .place(place)
                           .build();
        return sparkRepository.save(spark);
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
