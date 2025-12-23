package com.gordeev.postgresql.common.dto;

import lombok.*;

public record ApiError(@NonNull String message, @NonNull String code) {
}
