package com.gordeev.HRM.employee.dto;

import com.gordeev.HRM.common.enums.Departments;
import com.gordeev.HRM.common.enums.EmploymentTypes;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeRequest {
    // Personal Data
    @NotBlank(message = "ФИО обязательно")
    private String fullName;
    @Past
    private LocalDate birthDate;
    private String sex;
    private String passportSeries;
    private String passportNumber;
    private String address;

    // Contacts
    @NotBlank(message = "phone обязателен")
    private String phone;
    @Email
    @NotBlank(message = "email обязателен")
    private String email;

    // Details
    @NotNull(message = "Дата приёма на работу обязательна")
    @PastOrPresent
    private LocalDate hireDate;
    private String position;
    private Departments department;
    private EmploymentTypes employmentType;
    @DecimalMin("0.00")
    private BigDecimal salary;

}
