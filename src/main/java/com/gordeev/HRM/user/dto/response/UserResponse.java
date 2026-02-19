package com.gordeev.HRM.user.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String login,
        String fullName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
