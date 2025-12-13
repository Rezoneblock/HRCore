package com.gordeev.postgresql.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank(message = "username обязателен")
        String username,

        @NotBlank(message = "email обязателен")
        String email,

        String firstname,
        String lastname
) {
}
