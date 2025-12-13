package com.gordeev.postgresql.dto.response;

import java.time.LocalDateTime;

public record UserResponse (
        Long id,
        String username,
        String email,
        String firstname,
        String lastname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
