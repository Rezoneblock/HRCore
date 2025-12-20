package com.gordeev.postgresql.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiError {
    @NonNull
    private final String message;

    @NonNull
    private final String code;

}
