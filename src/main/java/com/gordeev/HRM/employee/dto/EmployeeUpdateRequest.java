package com.gordeev.HRM.employee.dto;

import com.gordeev.HRM.common.enums.EmployeeStatus;
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
    private String department;
    private String employmentType;
    private String workFrom;
    private BigDecimal salary;
}
