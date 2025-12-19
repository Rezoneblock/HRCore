package com.gordeev.postgresql.common.exception;

import lombok.Getter;

@Getter
public class EmailAlreadyExistsException extends RuntimeException {

    public final String CODE = "EMAIL_ALREADY_EXISTS";

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}