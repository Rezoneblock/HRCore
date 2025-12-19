package com.gordeev.postgresql.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ApiError {
    @NonNull
    private final String message;

    @NonNull
    private final String code;

    private final LocalDateTime timestamp = LocalDateTime.now();
}
