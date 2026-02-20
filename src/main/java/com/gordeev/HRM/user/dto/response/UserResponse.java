package com.gordeev.HRM.user.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String login,
        String fullName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
