package com.gordeev.postgresql.common.advice;

import com.gordeev.postgresql.common.dto.ApiError;
import com.gordeev.postgresql.common.dto.ApiResponse;
import com.gordeev.postgresql.common.exception.BusinessException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<@NonNull ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        ApiError error = new ApiError(ex.getMessage(), ex.getErrorCode());
        System.out.println(error.getCode());
        ApiResponse<Void> response = ApiResponse.error(error);

        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

}
