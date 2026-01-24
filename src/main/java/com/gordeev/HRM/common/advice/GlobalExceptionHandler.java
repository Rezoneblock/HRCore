package com.gordeev.HRM.common.advice;

import com.gordeev.HRM.common.dto.ApiError;
import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.common.exception.BusinessException;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<@NonNull ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        ApiError error = new ApiError(ex.getMessage(), ex.getErrorCode());
        ApiResponse<Void> response = ApiResponse.error(error);

        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String message = "Ошибка валидации: " + String.join(", ", errors.values());
        ApiError error = new ApiError(message, "VALIDATION_ERROR");
        ApiResponse<Void> response = ApiResponse.error(error);

        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        String message = "Ошибка валидации entity: " + String.join(", ", errors.values());
        ApiError error = new ApiError(message, "ENTITY_VALIDATION_ERROR");
        ApiResponse<Void> response = ApiResponse.error(error);

        return ResponseEntity.badRequest().body(response);
    }

}
