package com.gordeev.HRM.employee.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.employee.dto.EmployeeCreateRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.dto.EmployeeUpdateRequest;
import com.gordeev.HRM.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeResponse employee = employeeService.createEmployee(request);
        ApiResponse<EmployeeResponse> response = ApiResponse.success(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        ApiResponse<List<EmployeeResponse>> response = ApiResponse.success(employees);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedModel<EmployeeResponse>>> getEmployee(
            @RequestParam String fullName,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
            ) {

        Page<EmployeeResponse> page = employeeService.searchEmployees(fullName, pageable);

        PagedModel<EmployeeResponse> pagedModel = PagedModel.of(
                page.getContent(),
                new PagedModel.PageMetadata(
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalElements(),
                        page.getTotalPages()
                )
        );

        return ResponseEntity.ok(ApiResponse.success(pagedModel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> patchEmployee(
            @PathVariable UUID id,
            @RequestBody EmployeeUpdateRequest request
            ) {
        EmployeeResponse updatedEmployee =  employeeService.partialEmployeeUpdate(id, request);

        return ResponseEntity.ok(ApiResponse.success(updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}
