package com.onna.onnaback.domain.place.adapter.in.web;

import java.util.List;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceDetailInfo;
import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceResponse;
import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceSearchDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceReloadDto;
import com.onna.onnaback.domain.place.application.port.in.PlaceUseCase;
import com.onna.onnaback.domain.place.domain.PlaceType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SparkType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceUseCase placeUseCase;

    @Operation(description = "현 위치 주변 검색(재검색)")
    @GetMapping("/reload")
    public ResponseEntity<List<PlaceReloadDto>> reload(
            @RequestParam(value = "sparkType", required = false) SparkType sparkType,
            @RequestParam(value = "durationHour", required = false) DurationHour durationHour,
            @RequestParam(value = "placeType", required = false) PlaceType placeType,
            @RequestParam(value = "southwestLongitude") Double southwestLongitude,
            @RequestParam(value = "northeastLongitude") Double northeastLongitude,
            @RequestParam(value = "southwestLatitude") Double southwestLatitude,
            @RequestParam(value = "northeastLatitude") Double northeastLatitude
    ) {
        return ResponseEntity.ok().body(
                this.placeUseCase.reload(
                        sparkType,
                        durationHour,
                        placeType,
                        southwestLongitude, northeastLongitude,
                        southwestLatitude, northeastLatitude)
        );
    }

    @Operation(description = "장소 키워드 검색 API")
    @GetMapping("/search/{value}")
    public ResponseEntity<List<PlaceSearchDto>> searchPlace(@PathVariable("value")String value){
        return ResponseEntity.ok().body(this.placeUseCase.searchPlace(value));
    }

    @Operation(description = "장소 상세조회")
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceDetailInfo> getSparkDetail(@PathVariable("placeId") Long placeId)
    {
        return ResponseEntity.ok().body(this.placeUseCase.getPlaceInfo(placeId));
    }
}
