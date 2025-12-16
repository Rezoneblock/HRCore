package com.gordeev.postgresql.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {
    private boolean success; // Статус ответа
    private T data; // Успех
    private ApiError error; // Провал
    private final LocalDateTime timestamp = LocalDateTime.now(); // Дата

    // Ответ при успехе
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        return response;
    }

    // Ответ при ошибке
    public static <T> ApiResponse<T> error(ApiError error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.error = error;
        return response;
    }
}
