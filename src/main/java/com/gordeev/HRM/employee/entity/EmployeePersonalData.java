package com.gordeev.HRM.employee.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employee_personal_data")
public class EmployeePersonalData {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(
            name = "employee_id",
            nullable = false,
            unique = true
    )
    private Employee employee;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
