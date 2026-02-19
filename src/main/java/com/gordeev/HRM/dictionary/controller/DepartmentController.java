package com.gordeev.HRM.dictionary.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentCreateRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.SetOnboardingDepartmentsRequest;
import com.gordeev.HRM.dictionary.dto.response.department.DepartmentResponse;
import com.gordeev.HRM.dictionary.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartments() {
        List<DepartmentResponse> result = departmentService.getAllDepartments();
        ApiResponse<List<DepartmentResponse>> response = ApiResponse.success(result);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> createDepartment(@RequestBody List<@Valid DepartmentCreateRequest> request) {
        List<DepartmentResponse> result = departmentService.createDepartment(request);
        ApiResponse<List<DepartmentResponse>> response = ApiResponse.success(result);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/set-onboarding")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> setOnboardingDepartments(@RequestBody List<@Valid SetOnboardingDepartmentsRequest> request) {
        List<DepartmentResponse> result = departmentService.setOnboardingDepartments(request);

        ApiResponse<List<DepartmentResponse>> response = ApiResponse.success(result);

        return ResponseEntity.ok(response);
    }
}
