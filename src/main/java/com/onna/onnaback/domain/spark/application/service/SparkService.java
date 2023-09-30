package com.onna.onnaback.domain.spark.application.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.apply.application.port.out.LoadApplyPort;
import com.onna.onnaback.domain.member.application.port.in.MemberUseCase;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.application.port.in.PlaceUseCase;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.HostListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkApplyListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.spark.application.port.out.LoadSparkPort;
import com.onna.onnaback.domain.spark.application.port.out.SaveSparkPort;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SortType;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.domain.spark.domain.SparkType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SparkService implements SparkUseCase {
    private static final Integer PAGE_SIZE = 100;   // 한 페이지당 사이즈

    private final MemberUseCase memberUseCase;

    private final PlaceUseCase placeUseCase;
    private final LoadSparkPort loadSparkPort;
    private final LoadApplyPort loadApplyPort;
    private final SaveSparkPort saveSparkPort;

    /**
     * 스파크 미팅/클래스 정보를 반환합니다.
     * @return 스파크 미팅/클래스 정보
     */

    @Override
    @Transactional
    public Spark uploadSpark(Member host, HostDto hostDto) {
        Place place = placeUseCase.getById(hostDto.getPlaceId());
        return saveSparkPort.saveApply(host, place, hostDto);
    }

    @Override
    public List<SparkListDto> getList(int page, SparkType sparkType,
                                      DurationHour durationHour, SortType sortType,
                                      Double southwestLongitude, Double northeastLongitude,
                                      Double southwestLatitude, Double northeastLatitude) {
        return loadSparkPort.getList(PageRequest.of(page - 1, PAGE_SIZE),
                                     sparkType, durationHour, sortType,
                                     southwestLongitude, northeastLongitude,
                                     southwestLatitude, northeastLatitude);
    }

    @Override
    public List<HostListDto> getHostList(Member host) {
        return loadSparkPort.getHostList(host);
    }

    @Override
    public List<SparkApplyListDto> getSparkApplyList(Long sparkId) {
        return loadApplyPort.getSparkApplyList(sparkId);
    }

    @Override
    public List<SparkResponse> getSparkListByPlaceId(int page, int size, Long placeId) {

        return loadSparkPort.getSparkListByPlaceId(PageRequest.of(page - 1, size), placeId);
    }

    @Override
    public SparkResponse getSparkInfo(Long id) {
        return loadSparkPort.getSparkInfo(id);
    }

    @Override
    public Spark getById(Long sparkId) {
        return loadSparkPort.getById(sparkId);
    }

    @Override
    public Spark increaseMemberCount(Spark spark) {
        return spark.increaseMemberCount();
    }
}
