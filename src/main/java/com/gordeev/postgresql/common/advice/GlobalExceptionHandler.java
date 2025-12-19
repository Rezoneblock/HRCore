package com.gordeev.postgresql.common.advice;

import com.gordeev.postgresql.common.dto.ApiError;
import com.gordeev.postgresql.common.dto.ApiResponse;
import com.gordeev.postgresql.common.exception.EmailAlreadyExistsException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<@NonNull ApiResponse<Void>> handleEmailExists(EmailAlreadyExistsException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), ex.getCODE());
        ApiResponse<Void> response = ApiResponse.error(apiError);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
