package com.gordeev.HRM.dictionary.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.dictionary.dto.request.EmploymentTypesCreateRequest.EmploymentTypesCreateRequest;
import com.gordeev.HRM.dictionary.dto.response.employmentType.EmploymentTypeResponse;
import com.gordeev.HRM.dictionary.service.EmploymentModeService;
import com.gordeev.HRM.dictionary.service.EmploymentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employment-types")
@RequiredArgsConstructor
public class EmploymentTypeController {
    private final EmploymentTypeService employmentTypeService;

    private final EmploymentModeService employmentModeService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<EmploymentTypeResponse>>> createEmploymentTypes(@RequestBody @Valid EmploymentTypesCreateRequest request) {
        List<EmploymentTypeResponse> result = employmentTypeService.createEmploymentTypes(request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmploymentTypeResponse>>> getEmploymentTypes() {
        List<EmploymentTypeResponse> result = employmentTypeService.getEmploymentTypes();

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Void>> deleteEmploymentType(@PathVariable String code) {
        employmentTypeService.deleteEmploymentType(code);

        return ResponseEntity.noContent().build();
    }
}
