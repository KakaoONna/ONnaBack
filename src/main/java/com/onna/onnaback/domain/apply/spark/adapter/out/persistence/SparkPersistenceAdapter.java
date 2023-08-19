package com.onna.onnaback.domain.apply.spark.adapter.out.persistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.onna.onnaback.domain.apply.spark.adater.in.web.request.HostDto;
import com.onna.onnaback.domain.apply.spark.application.port.out.LoadSparkPort;
import com.onna.onnaback.domain.apply.spark.application.port.out.SaveSparkPort;
import com.onna.onnaback.domain.apply.spark.domain.Spark;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.domain.Place;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SparkPersistenceAdapter implements LoadSparkPort, SaveSparkPort {

    private final SparkRepository sparkRepository;

    @Override
    public Optional<Spark> getById(Long sparkId) {
        // todo: orElseThrow 추가
        return sparkRepository.findById(sparkId);
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
}
