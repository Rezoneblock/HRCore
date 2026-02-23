package com.gordeev.HRM.dictionary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dict_employment_types")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code; // FULLTIME, PARTTIME, ...

    @Column(nullable = false)
    private Boolean active = true;
}
