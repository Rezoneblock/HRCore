package com.gordeev.HRM.employee.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "employment_details")
public class EmploymentDetails {

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

    private String position;
    private String department;
    private String employmentType; // фуллтайм, парттайм или сдельно (contract)
}
