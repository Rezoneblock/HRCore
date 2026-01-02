package com.gordeev.HRM.common.enums;

import lombok.Getter;

@Getter
public enum EmployeeStatus {
    ACTIVE("Работает"),
    ON_VACATION("В отпуске"),
    ON_SICK_LEAVE("На больничном"),
    METERNITY_LEAVE("В декретном отпуске"),
    TERMINATED("Уволен");

    private final String description;

    EmployeeStatus(String description) {
        this.description = description;
    }
}
