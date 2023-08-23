package com.onna.onnaback.domain.spark.adapter.out.persistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.place.adapter.out.persistence.PlaceRepository;
import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.ParticipateMemberDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.application.port.out.SaveSparkPort;
import com.onna.onnaback.domain.spark.domain.Spark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import com.onna.onnaback.domain.spark.application.port.out.LoadSparkPort;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.domain.Place;

import lombok.RequiredArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
        Place place=placeRepository.findByPlaceId(placeId);
        System.err.println(place.getName());
        return sparkRepository.findSparksByPlace(place,pageable).getContent().stream().map(spark ->
                SparkResponse.builder()
                        .sparkId(spark.getSparkId())
                        .description(spark.getDescription())
                        .placeId(spark.getPlace().getPlaceId())
                        .detailAddress(spark.getPlace().getDetailAddress())
                        .lat(spark.getPlace().getLatitude())
                        .lng(spark.getPlace().getLongitude())
                        .sparkDate(spark.getSparkDate())
                        .price(spark.getPrice())
                        .sparkType(spark.getType())
                        .hostImg(spark.getHost().getProfileImg())
                        .title(spark.getTitle())
                        .hostName(spark.getHost().getName())
                        .recruitType(spark.getRecruitType())
                        .participateMember(spark.getMemberSparkMappingList().stream().map(
                                memberSparkMapping -> ParticipateMemberDto.builder()
                                        .memberId(memberSparkMapping.getApplicant().getMemberId())
                                        .profileImg(memberSparkMapping.getApplicant().getProfileImg())
                                        .build()
                        ).collect(Collectors.toList()))
                        .durationHour(spark.getDurationHour())
                        .hostDetail(spark.getHostDetail())
                        .memberCount(spark.getMemberCount())
                        .capacity(spark.getCapacity())
                        .build()).collect(Collectors.toList());
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

    private Specification<Spark> equalPlace(Long placeId){
        return new Specification<Spark>() {
            @Override
            public Predicate toPredicate(Root<Spark> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("placeId"),placeId);
            }
        };
    }


}
