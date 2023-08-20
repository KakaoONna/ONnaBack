package com.onna.onnaback.domain.spark.adapter.in.web;

import com.onna.onnaback.domain.spark.adapter.in.web.response.SparkResponse;
import com.onna.onnaback.domain.spark.application.port.in.SparkUseCase;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/spark")
@RequiredArgsConstructor
public class SparkController {

    private final SparkUseCase sparkUseCase;

    @Operation(description = "장소별 동행컨텐츠 조회")
    @GetMapping("/{placeId}")
    public ResponseEntity<List<SparkResponse>> getSparkList(@PathVariable("placeId") Long placeId,
                                                            @RequestParam(value="page",required = false,defaultValue = "1")int page,
                                                            @RequestParam(value="size",required = false,defaultValue = "5")int size){

        return ResponseEntity.ok().body(
                this.sparkUseCase.getSparkListByPlaceId(page,size,placeId)
        );
    }
}
