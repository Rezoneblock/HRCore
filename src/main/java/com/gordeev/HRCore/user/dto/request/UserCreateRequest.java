package com.gordeev.HRCore.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserCreateRequest (
        @NotBlank(message = "username обязателен")
        String username,

        @NotBlank(message = "email обязателен")
        @Email(message = "Некорректный email")
        String email,

        @NotBlank(message = "password обязателен")
        String password,

        String firstname,
        String lastname
) {}
