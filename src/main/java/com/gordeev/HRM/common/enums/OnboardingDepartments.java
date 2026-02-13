package com.gordeev.HRM.common.enums;

public enum OnboardingDepartments {
    IT("Администрирование информационных систем"),
    FINANCE("Бухгалтерия"),
    MEDICAL("Здоровье"),
    SUPPLY("Канцелярия");

    private final String description;

    OnboardingDepartments(String description) {
        this.description = description;
    }
}
