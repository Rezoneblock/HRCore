package com.gordeev.HRCore.user.exception;

import com.gordeev.HRCore.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsersPageEmptyException extends BusinessException {

    public static final String CODE = "PAGE_IS_EMPTY";

    public UsersPageEmptyException(String message) {
        super(CODE, message, HttpStatus.NOT_FOUND);
    }
}
