package com.gordeev.HRM.dictionary.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.dictionary.dto.response.DepartmentResponse;
import com.gordeev.HRM.dictionary.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartments() {
        List<DepartmentResponse> result = departmentService.getAllDepartments();
        ApiResponse<List<DepartmentResponse>> response = ApiResponse.success(result);

        return ResponseEntity.ok(response);
    }
}
