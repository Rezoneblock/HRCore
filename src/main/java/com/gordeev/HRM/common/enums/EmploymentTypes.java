package com.gordeev.HRM.common.enums;

import lombok.Getter;

@Getter
public enum EmploymentTypes {
    FULLTIME("Полный рабочий день"),
    PARTTIME("Неполный рабочий день"),
    CONTRACT("Сдельный режим работы");

    private final String description;

    EmploymentTypes(String description) {
        this.description = description;
    }
}
