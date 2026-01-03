package com.gordeev.HRM.employee.controller;

import com.gordeev.HRM.employee.dto.CreateEmployeeRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        return employeeService.createEmployee(request);
    }
}
