package com.gordeev.HRM.dictionary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dict_employment_modes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code; // REMOTE, OFFICE, HYBRID, ...

    @Column(nullable = false)
    private Boolean active = true;
}
