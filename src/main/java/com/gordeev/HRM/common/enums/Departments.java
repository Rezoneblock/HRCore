package com.gordeev.HRM.common.enums;

import lombok.Getter;

@Getter
public enum Departments {
    IT("Администрирование информационных систем"),
    FINANCE("Бухгалтерия"),
    HR("Кадры"),
    MEDICAL("Здоровье"),
    SUPPLY("Мат. обеспечение"),
    MARKETING("Маркетинг"),
    SALES("Продажи"),
    MANAMEGENT("Руководство");

    private final String description;

    Departments(String description) {
        this.description = description;
    }
}
