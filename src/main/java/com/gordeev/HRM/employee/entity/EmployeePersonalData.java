package com.gordeev.HRM.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employee_personal_data")
@Getter
@Setter
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

    @NotBlank
    @Column(length = 40)
    private String fullName;

    @Column(length = 7)
    private String sex;

    @Past
    private LocalDate birthDate;

    @Column(length = 10)
    private String passportSeries;

    @Column(length = 10)
    private String passportNumber;

    @Column(length = 100)
    private String address;
}
