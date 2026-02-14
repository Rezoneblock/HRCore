package com.gordeev.HRM.companyInitialize.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record EmployeeFullCreateRequest(
        @NotBlank(message = "username обязателен")
        String username,

        @NotBlank(message = "email обязателен")
        @Email(message = "Некорректный email")
        String email,

        @NotBlank(message = "password обязателен")
        String password,

        @NotBlank(message = "Полное ФИО обязательно")
        String fullName,

        @NotNull(message = "Роль обязательна")
        Set<String> role
) {
}
