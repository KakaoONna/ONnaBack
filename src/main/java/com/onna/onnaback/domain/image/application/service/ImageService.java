package com.onna.onnaback.domain.image.application.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.onna.onnaback.domain.image.application.port.in.ImageUseCase;
import com.onna.onnaback.domain.image.exceptions.NotSupportedFileFormatException;
import com.onna.onnaback.domain.spark.application.port.out.UploadS3Port;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService implements ImageUseCase {

    private static final List<String> FILETYPE = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/jpg"
    );

    private final UploadS3Port uploadS3Port;

    /**
     * 이미지를 s3에 업로드합니다.
     * @return 업로드된 이미지 url
     */
    @Override
    public String uploadImage(MultipartFile multipartFile) {
        mimeValidation(multipartFile.getContentType());
        return uploadS3Port.uploadS3(multipartFile);
    }

    private void mimeValidation(String mime) {
        if (!FILETYPE.contains(mime)) {
            throw new NotSupportedFileFormatException(mime);
        }
    }
}
