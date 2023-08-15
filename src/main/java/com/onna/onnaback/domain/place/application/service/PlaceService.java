package com.onna.onnaback.domain.place.application.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onna.onnaback.domain.place.application.port.in.PlaceUseCase;
import com.onna.onnaback.domain.place.application.port.out.LoadPlacePort;
import com.onna.onnaback.domain.place.domain.Place;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService implements PlaceUseCase {
    private static final Integer PAGE_SIZE = 100;   // 한 페이지당 사이즈
    private final LoadPlacePort loadPlacePort;

    @Override
    public List<Place> reload(
            int page,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    ) {
        // todo: 부산 외곽의 경우 에러 처리

        return loadPlacePort.getList(PageRequest.of(page - 1, PAGE_SIZE),
                                     southwestLongitude, northeastLongitude,
                                     southwestLatitude, northeastLatitude);
    }
}
