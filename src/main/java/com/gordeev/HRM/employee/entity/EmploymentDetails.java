package com.gordeev.HRM.employee.entity;

import com.gordeev.HRM.common.enums.Departments;
import com.gordeev.HRM.common.enums.EmploymentTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employment_details")
@Getter
@Setter
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

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Departments department;

    @Enumerated(EnumType.STRING)
    private EmploymentTypes employmentType; // фуллтайм, парттайм или сдельно (contract)


    private BigDecimal salary;
}
