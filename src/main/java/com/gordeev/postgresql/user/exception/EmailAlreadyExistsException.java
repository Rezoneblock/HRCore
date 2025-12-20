package com.gordeev.postgresql.user.exception;

import com.gordeev.postgresql.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailAlreadyExistsException extends BusinessException {

    public static final String CODE = "EMAIL_ALREADY_EXISTS";

    public EmailAlreadyExistsException(String message) {

        super(CODE, message, HttpStatus.CONFLICT);
    }
}