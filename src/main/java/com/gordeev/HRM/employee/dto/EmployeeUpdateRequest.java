package com.gordeev.HRM.employee.dto;

import com.gordeev.HRM.common.enums.Departments;
import com.gordeev.HRM.common.enums.EmployeeStatus;
import com.gordeev.HRM.common.enums.EmploymentTypes;
import com.gordeev.HRM.common.enums.WorkFrom;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class EmployeeUpdateRequest {
    // Hirement status
    private EmployeeStatus status;

    // Personal data
    private String fullName;
    private LocalDate birthDate;
    private String sex;
    private String passportSeries;
    private String passportNumber;
    private String address;

    // Contacts
    private String phone;
    private String email;

    // Details
    private String position;
    private Departments department;
    private EmploymentTypes employmentType;
    private WorkFrom workFrom;
    private BigDecimal salary;
}
