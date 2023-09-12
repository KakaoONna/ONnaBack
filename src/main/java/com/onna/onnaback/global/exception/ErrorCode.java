package com.onna.onnaback.global.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // GENERAL
    SUCCESS(HttpStatus.OK, 200, "요청에 성공하였습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "입력값을 확인해주세요."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 403, "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 404, "대상을 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 403, "인가되지 않은 사용자입니다."),

    INVALID_HTTP_METHOD(METHOD_NOT_ALLOWED, 405, "잘못된 Http Method 요청입니다."),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, 400, "잘못된 입력값입니다."),
    SERVER_INTERNAL_ERROR(INTERNAL_SERVER_ERROR, 500, "서버 내부에 오류가 발생했습니다."),

    //BUSINESS LOGIC EXCEPTION
    SPARK_NOT_FOUND(INTERNAL_SERVER_ERROR, 404, "해당 스파크 컨텐츠가 존재하지 않습니다."),
    APPLY_ALREADY(HttpStatus.BAD_REQUEST, 405, "이미 신청한 스파크 컨텐츠입니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int code, String message) {

        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
