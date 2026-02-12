package com.gordeev.HRM.common.enums;

public enum OnboardingStatus {
    PENDGING("Ожидает"),
    IN_PROGRESS("В обработке"),
    READY("Готов"),
    BLOCKED("Заблокирован"),
    FAILED("Не удалось настроить");

    private final String description;

    OnboardingStatus(String description) {
        this.description = description;
    }
}
