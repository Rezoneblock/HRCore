package com.gordeev.HRM.employee.entity;

import com.gordeev.HRM.common.enums.EmployeeStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    private LocalDate hireDate;

    @OneToOne(mappedBy = "employee")
    private EmployeePersonalData personalData;

    @OneToOne(mappedBy = "employee")
    private EmployeeContacts contacts;

    @OneToOne(mappedBy = "employee")
    private EmploymentDetails employmentDetails;
}
