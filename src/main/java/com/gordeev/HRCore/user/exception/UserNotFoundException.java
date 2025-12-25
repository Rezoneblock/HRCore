package com.gordeev.HRCore.user.exception;

import com.gordeev.HRCore.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {
    public static final String CODE = "USER_NOT_FOUND";

    public UserNotFoundException(String message) {
        super(CODE, message, HttpStatus.NOT_FOUND);
    }
}
