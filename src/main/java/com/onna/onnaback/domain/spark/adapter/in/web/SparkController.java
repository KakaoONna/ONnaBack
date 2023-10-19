package com.onna.onnaback.domain.spark.adapter.in.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onna.onnaback.domain.member.application.service.CustomUserDetails;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.spark.adapter.in.web.request.HostDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.HostListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkApplyListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkHostResponse;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkListDto;
import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.application.port.in.SparkUseCase;
import com.onna.onnaback.domain.spark.domain.Always;
import com.onna.onnaback.domain.spark.domain.CapacityType;
import com.onna.onnaback.domain.spark.domain.DurationHour;
import com.onna.onnaback.domain.spark.domain.SortType;
import com.onna.onnaback.domain.spark.domain.Spark;
import com.onna.onnaback.domain.spark.domain.SparkType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/spark")
@RequiredArgsConstructor
public class SparkController {

    private final SparkUseCase sparkUseCase;

    @Operation(description = "장소별 동행컨텐츠 조회")
    @GetMapping("/place/{placeId}")
    public ResponseEntity<List<SparkResponse>> getSparkContentList(@PathVariable("placeId") Long placeId) {
        return ResponseEntity.ok().body(
                this.sparkUseCase.getSparkListByPlaceId(placeId)
        );
    }

    @Operation(description = "스파크 상세조회")
    @GetMapping("/{sparkId}")
    public ResponseEntity<SparkResponse> getSparkDetail(@PathVariable("sparkId") Long sparkId) {
        return ResponseEntity.ok().body(this.sparkUseCase.getSparkInfo(sparkId));
    }

    @Operation(description = "주최하기")
    @PostMapping(value = "/host", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<SparkHostResponse> host(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @RequestParam("placeId") Long placeId,
                                                  @RequestParam("title") String title,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("type") SparkType type,
                                                  @RequestParam("always") Always always,
                                                  @RequestParam("sparkDate") String sparkDate,
                                                  @RequestParam("price") Long price,
                                                  @RequestParam("capacityType") CapacityType capacityType,
                                                  @RequestParam("capacity") Long capacity,
                                                  @RequestParam("durationHour") DurationHour durationHour,
                                                  @RequestParam("hostDetail") String hostDetail,
                                                  @RequestParam("chatUrl") String chatUrl,
                                                  @RequestParam(value = "img", required = false)
                                                          MultipartFile img
    ) {
        HostDto hostDto = new HostDto(img, placeId, title, description, type, always, sparkDate, price,
                                      capacityType,
                                      capacity, durationHour, hostDetail, chatUrl);

        Member host = customUserDetails.getMember();
        Spark spark = this.sparkUseCase.uploadSpark(host, hostDto);
        return ResponseEntity.ok().body(
                new SparkHostResponse(spark.getSparkId(), spark.getImg())
        );
    }

    @Operation(description = "주최 내역 확인하기")
    @GetMapping("/list/host")
    public ResponseEntity<List<HostListDto>> getHostList(@AuthenticationPrincipal
                                                                 CustomUserDetails customUserDetails) {
        Member host = customUserDetails.getMember();
        return ResponseEntity.ok().body(sparkUseCase.getHostList(host));
    }

    @Operation(description = "주최 내역 - 신청 목록")
    @GetMapping("/list/apply/{sparkId}")
    public ResponseEntity<List<SparkApplyListDto>> getSparkApplyList(@PathVariable("sparkId") Long sparkId) {
        return ResponseEntity.ok().body(sparkUseCase.getSparkApplyList(sparkId));
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

    @Operation(description = "스파크 키워드 검색 API")
    @GetMapping("/search/{value}")
    public ResponseEntity<List<SparkResponse>> searchSpark(@PathVariable("value") String value) {
        return ResponseEntity.ok().body(this.sparkUseCase.searchSpark(value));
    }

}
