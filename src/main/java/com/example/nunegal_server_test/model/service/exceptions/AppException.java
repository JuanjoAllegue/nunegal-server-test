package com.example.nunegal_server_test.model.service.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AppException extends Exception {
    private HttpStatus status;

    private String errorCode;

    public AppException(String errorCode, HttpStatus status) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
