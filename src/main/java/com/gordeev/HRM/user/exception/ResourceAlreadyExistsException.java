package com.gordeev.HRM.user.exception;

import com.gordeev.HRM.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceAlreadyExistsException extends BusinessException {

    public static final String CODE = "RESOURCE_ALREADY_EXISTS";

    public ResourceAlreadyExistsException(String message) {

        super(CODE, message, HttpStatus.CONFLICT);
    }
}