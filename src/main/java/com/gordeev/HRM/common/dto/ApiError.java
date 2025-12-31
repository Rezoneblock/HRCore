package com.gordeev.HRM.common.dto;

import lombok.*;

public record ApiError(@NonNull String message, @NonNull String code) {
}
