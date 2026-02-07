package com.gordeev.HRM.common.enums;

import lombok.Getter;

@Getter
public enum WorkFrom {
    REMOTE("Удаленно"),
    OFFICE("Офис"),
    HYBRID("Гибрид");

    private final String description;

    WorkFrom(String description) {
        this.description = description;
    }
}
