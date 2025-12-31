package com.gordeev.HRM.employee.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "employee_contacts")
public class EmployeeContacts {

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

    private String email;
    private String phone;
    private String address;
}
