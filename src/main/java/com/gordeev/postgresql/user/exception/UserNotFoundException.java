package com.gordeev.postgresql.user.exception;

public class UserNotFoundException extends RuntimeException{
    public final String CODE = "USER_NOT_FOUND";
    public UserNotFoundException(String message) {
        super(message);
    }
}
