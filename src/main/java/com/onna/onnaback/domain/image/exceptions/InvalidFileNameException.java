package com.onna.onnaback.domain.image.exceptions;

public class InvalidFileNameException extends RuntimeException {
    public InvalidFileNameException() {
        super("파일이름이 형식에 맞지 않습니다.");
    }
}
