package com.onna.onnaback.domain.place.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onna.onnaback.domain.place.adapter.in.web.response.PlaceResponse;
import com.onna.onnaback.domain.place.application.port.in.PlaceUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceUseCase placeUseCase;

    @GetMapping("/reload")
    public ResponseEntity<List<PlaceResponse>> reload(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "southwestLongitude") Double southwestLongitude,
            @RequestParam(value = "northeastLongitude") Double northeastLongitude,
            @RequestParam(value = "southwestLatitude") Double southwestLatitude,
            @RequestParam(value = "northeastLatitude") Double northeastLatitude
    ) {
        return ResponseEntity.ok().body(
                this.placeUseCase.reload(
                            page,
                            southwestLongitude, northeastLongitude,
                            southwestLatitude, northeastLatitude)
                                 .stream().map(PlaceResponse::new)
                                 .collect(Collectors.toList())
        );
    }
}
