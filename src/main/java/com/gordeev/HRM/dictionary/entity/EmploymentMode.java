package com.gordeev.HRM.dictionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dict_employment_modes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code; // REMOTE, OFFICE, HYBRID, ...

    @Column(nullable = false, length = 200)
    private String name; // Удаленно, в офисе, гибрид, ...

    @Column(length = 500)
    private String description;

    private boolean active = true;
}
