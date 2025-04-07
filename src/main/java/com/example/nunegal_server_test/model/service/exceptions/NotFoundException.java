package com.example.nunegal_server_test.model.service.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {
    public NotFoundException(String errorCode) {
        super(errorCode, HttpStatus.NOT_FOUND);
    }
}
