package com.gordeev.HRM.common.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gordeev.HRM.common.dto.ApiError;
import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.common.exception.BusinessException;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        ApiError error = null;
        String errorMessage = ex.getMessage();

        if (errorMessage.contains("JSON parse error")) {
            error = new ApiError("Ошибка формата JSON", "JSON_PARSE_ERROR");
        }

        if (cause instanceof InvalidFormatException invalidFormatException) {
            if (invalidFormatException.getTargetType().isEnum()) {
                String fieldName = !invalidFormatException.getPath().isEmpty() ? invalidFormatException.getPath().getFirst().getFieldName() : "field";

                error = new ApiError(String.format("Недопустимое значение для %s", fieldName), "INVALID_ENUM_VALUE");
            }
        }

        if (error == null) {
            error = new ApiError("Необработанная 'HttpMessageNotReadableException' ошибка...", "BAD_REQUEST");
        }

        ApiResponse<Void> response = ApiResponse.error(error);
        return ResponseEntity.badRequest().body(response);
    }
}
