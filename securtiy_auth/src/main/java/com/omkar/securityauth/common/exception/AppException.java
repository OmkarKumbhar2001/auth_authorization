package com.omkar.securityauth.common.exception;

import com.omkar.securityauth.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus status;

    public AppException(ErrorCode errorCode, HttpStatus status) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.status = status;
    }
}
