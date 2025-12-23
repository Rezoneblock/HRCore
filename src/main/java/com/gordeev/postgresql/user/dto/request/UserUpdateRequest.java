package com.gordeev.postgresql.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @Email
        String email,
        String username,
        String firstname,
        String lastname
) {
}
