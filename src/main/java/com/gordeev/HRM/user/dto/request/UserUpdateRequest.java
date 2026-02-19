package com.gordeev.HRM.user.dto.request;

public record UserUpdateRequest(
        String login,
        String fullName
) {
}
