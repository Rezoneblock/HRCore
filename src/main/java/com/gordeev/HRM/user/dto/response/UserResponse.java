package com.gordeev.HRM.user.dto.response;

import com.gordeev.HRM.employee.entity.Employee;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String login,
        String fullName,
        Boolean active,
        Set<String> roles,
        Employee employee,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
