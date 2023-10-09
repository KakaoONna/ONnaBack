package com.onna.onnaback.domain.image.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUseCase {

    String uploadImage(MultipartFile multipartFile);
}
