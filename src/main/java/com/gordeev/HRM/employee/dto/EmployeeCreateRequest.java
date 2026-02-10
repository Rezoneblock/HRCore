package com.gordeev.HRM.employee.dto;

import com.gordeev.HRM.common.enums.Departments;
import com.gordeev.HRM.common.enums.EmploymentTypes;
import com.gordeev.HRM.common.enums.WorkFrom;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateRequest {
    // Personal Data
    @NotBlank(message = "ФИО обязательно")
    private String fullName;
    @NotNull(message = "Дата рождения обязательна")
    @Past
    private LocalDate birthDate;
    @NotBlank(message = "Пол обязателен")
    private String sex;
    @NotBlank(message = "Серия паспорта обязательна")
    private String passportSeries;
    @NotBlank(message = "Номер паспорта обязателен")
    private String passportNumber;
    @NotBlank(message = "Адрес проживания обязателен")
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
    @NotBlank(message = "Должность обязательна")
    private String position;
    @NotNull(message = "Отдел обязателен")
    private Departments department;
    @NotNull(message = "Режим работы обязателен")
    private EmploymentTypes employmentType;
    @NotNull(message = "Формат работы обязателен")
    private WorkFrom workFrom;
    @NotNull(message = "Зарплата обязательна")
    @Positive
    private BigDecimal salary;

}
