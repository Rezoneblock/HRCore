package com.gordeev.HRM.user.dto.request;

import jakarta.validation.constraints.Email;

public record UserUpdateRequest(
        @Email
        String email,
        String username,
        String fullName
) {
}
