package com.gordeev.HRM.employee.dto;

import com.gordeev.HRM.common.enums.Departments;
import com.gordeev.HRM.common.enums.EmployeeStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class EmployeeResponse {
    private UUID id;
    private EmployeeStatus status;

    // Personal data
    private String fullName;
    private LocalDate birthDate;
    private String sex;

    // Contacts
    private String email;
    private String phone;

    // Details
    private String position;
    private Departments departments;
    private BigDecimal salary;
    private LocalDate hireDate;
}
