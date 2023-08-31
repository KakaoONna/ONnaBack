package com.onna.onnaback.domain.spark.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SortType;
import com.onna.onnaback.domain.spark.domain.SparkType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/spark")
@RequiredArgsConstructor
public class SparkController {

    private final SparkUseCase sparkUseCase;

    @Operation(description = "장소별 동행컨텐츠 조회")
    @GetMapping("/{placeId}")
    public ResponseEntity<List<SparkResponse>> getSparkList(@PathVariable("placeId") Long placeId,
                                                            @RequestParam(value = "page", required = false,
                                                                    defaultValue = "1") int page,
                                                            @RequestParam(value = "size", required = false,
                                                                    defaultValue = "5") int size
    ) {
        return ResponseEntity.ok().body(
                this.sparkUseCase.getSparkListByPlaceId(page, size, placeId)
        );
    }

    @Operation(description = "주최하기")
    @PostMapping("/host")
    public String host(@RequestBody HostDto hostDto) {
        return sparkUseCase.uploadSpark(hostDto);
    }

    @Operation(description = "필터에 맞는 리스트 반환")
    @GetMapping("/list")
    public ResponseEntity<List<SparkListDto>> getList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "sparkType", required = false) SparkType sparkType,
            @RequestParam(value = "durationHour", required = false) DurationHour durationHour,
            @RequestParam(value = "sort", required = false) SortType sortType,
            @RequestParam(value = "southwestLongitude") Double southwestLongitude,
            @RequestParam(value = "northeastLongitude") Double northeastLongitude,
            @RequestParam(value = "southwestLatitude") Double southwestLatitude,
            @RequestParam(value = "northeastLatitude") Double northeastLatitude
    ) {
        return ResponseEntity.ok().body(
                this.sparkUseCase.getList(
                        page,
                        sparkType,
                        durationHour,
                        sortType,
                        southwestLongitude, northeastLongitude,
                        southwestLatitude, northeastLatitude)
        );
    }
}
