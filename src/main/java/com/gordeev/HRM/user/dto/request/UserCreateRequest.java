package com.gordeev.HRM.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserCreateRequest (
        @NotBlank(message = "login обязателен")
        String login,

        @NotBlank(message = "Полное ФИО обязательно")
        String fullName
) {}
