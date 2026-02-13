package com.gordeev.HRM.common.enums;

public enum RoleType {
    ADMIN("Суперадмин"),
    HR("Кадровик"),

    IT_HEAD("Глава IT отдела"),
    FINANCE_HEAD("Глава отдела финансов"),
    MEDICAL_HEAD("Глава отдела здравохранения"),
    SUPPLY_HEAD("Глава отдела мат. обеспечения"),

    IT_EMPLOYEE("Сотрудник IT отдела"),
    FINANCE_EMPLOYEE("Сотрудник отдела финансов"),
    MEDICAL_EMPLOYEE("Сотрудник отдела здравохранения"),
    SUPPLY_EMPLOYEE("Сотрудник отдела мат. обеспечения");


    private final String description;

    RoleType(String description) {
        this.description = description;
    }
}
