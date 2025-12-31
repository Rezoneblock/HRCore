package com.gordeev.HRM.user.exception;

import com.gordeev.HRM.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsersPageEmptyException extends BusinessException {

    public static final String CODE = "PAGE_IS_EMPTY";

    public UsersPageEmptyException(String message) {
        super(CODE, message, HttpStatus.NOT_FOUND);
    }
}
