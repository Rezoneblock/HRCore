package com.gordeev.HRM.employee.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.employee.dto.CreateEmployeeRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.entity.Employee;
import com.gordeev.HRM.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        EmployeeResponse employee = employeeService.createEmployee(request);
        ApiResponse<EmployeeResponse> response = ApiResponse.success(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
