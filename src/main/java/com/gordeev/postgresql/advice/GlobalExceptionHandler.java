package com.gordeev.postgresql.advice;

import com.gordeev.postgresql.dto.response.ApiError;
import com.gordeev.postgresql.dto.response.ApiResponse;
import com.gordeev.postgresql.exception.EmailAlreadyExistsException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<@NonNull ApiResponse<Object>> handleEmailExists(EmailAlreadyExistsException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), "EMAIL_ALREADY_EXISTS");

    }
}
