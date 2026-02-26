package com.gordeev.HRM.employee.entity;

import com.gordeev.HRM.common.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employee_employment_details")
@Getter
@Setter
public class EmploymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    private String department;

    @Column(nullable = false)
    private String employmentType; // фуллтайм, парттайм или сдельно (contract)

    @Column(nullable = false)
    private String workFrom; // Удаленка, офис, гибрид.

    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status;

    @Column(name = "service_number", insertable = false, updatable = false)
    @Generated
    private Long serviceNumber;
}
