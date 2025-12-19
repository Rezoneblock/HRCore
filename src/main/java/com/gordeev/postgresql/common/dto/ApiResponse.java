package com.gordeev.postgresql.common.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {
    // Тело ответа:
    private boolean success; // Статус ответа
    private T data; // Успех
    private ApiError error; // Ошибка
    private final LocalDateTime timestamp = LocalDateTime.now(); // Дата

    // Ответ при успехе
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        return response;
    }

    // Ответ при ошибке
    public static ApiResponse<Void> error(ApiError error) {
        ApiResponse<Void> response = new ApiResponse<>();
        response.success = false;
        response.error = error;
        return response;
    }
}
