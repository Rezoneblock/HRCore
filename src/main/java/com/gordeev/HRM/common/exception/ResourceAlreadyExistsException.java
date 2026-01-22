package com.gordeev.HRM.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceAlreadyExistsException extends BusinessException {

    public static final String CODE = "RESOURCE_ALREADY_EXISTS";

    public ResourceAlreadyExistsException(String message) {

        super(CODE, message, HttpStatus.CONFLICT);
    }
}