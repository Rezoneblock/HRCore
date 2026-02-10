package com.gordeev.HRM.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceDoesNotExistException extends BusinessException {
    public static final String CODE = "RESOURCE_DOES_NOT_EXISTS";

    public ResourceDoesNotExistException(String message) {

        super(CODE, message, HttpStatus.CONFLICT);
    }
}
