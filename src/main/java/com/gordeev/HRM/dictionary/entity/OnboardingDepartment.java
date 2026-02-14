package com.gordeev.HRM.dictionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dict_onboarding_departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code; // IT, FINANCE, SUPPLY, ...

    @Column(nullable = false, length = 200)
    private String name; // Администрирование информационных систем, отдел кадров, ...

    @Column(length = 500)
    private String description;

    private boolean active = true;
}
