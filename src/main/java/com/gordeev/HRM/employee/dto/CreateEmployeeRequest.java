package com.gordeev.HRM.employee.dto;

import com.gordeev.HRM.common.enums.Departments;
import com.gordeev.HRM.common.enums.EmploymentTypes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeRequest {
    // Personal Data
    @NotBlank
    private String fullName;
    @Past
    private LocalDate birthDate;
    private String sex;
    private String passportSeries;
    private String passportNumber;
    private String address;

    // Contacts
    private String phone;
    @Email
    private String email;

    // Details
    private String position;
    private Departments department;
    private EmploymentTypes employmentType;
    private BigDecimal salary;

}
