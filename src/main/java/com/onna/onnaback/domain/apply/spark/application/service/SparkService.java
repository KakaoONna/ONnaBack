package com.onna.onnaback.domain.apply.spark.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.apply.spark.adater.in.web.request.HostDto;
import com.onna.onnaback.domain.apply.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.apply.spark.application.port.out.LoadSparkPort;
import com.onna.onnaback.domain.apply.spark.application.port.out.SaveSparkPort;
import com.onna.onnaback.domain.apply.spark.domain.Spark;
import com.onna.onnaback.domain.member.application.port.in.MemberUseCase;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.application.port.in.PlaceUseCase;
import com.onna.onnaback.domain.place.domain.Place;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SparkService implements SparkUseCase {

    private final MemberUseCase memberUseCase;
    private final PlaceUseCase placeUseCase;
    private final LoadSparkPort loadSparkPort;
    private final SaveSparkPort saveSparkPort;

    /**
     * 스파크 미팅/클래스 정보를 반환합니다.
     * @param sparkId 스파크 아이디
     * @return 스파크 미팅/클래스 정보
     */
    @Override
    public Spark getById(Long sparkId) {
        return loadSparkPort.getById(sparkId).orElseThrow();
    }

    @Override
    @Transactional
    public String uploadSpark(HostDto hostDto) {
        Member member = memberUseCase.getById(hostDto.getHostId());
        Place place = placeUseCase.getById(hostDto.getPlaceId());
        return saveSparkPort.saveApply(member, place, hostDto);
    }
}
