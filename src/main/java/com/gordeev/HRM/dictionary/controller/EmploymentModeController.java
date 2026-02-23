package com.gordeev.HRM.dictionary.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.dictionary.dto.request.employmentMode.EmploymentModesCreateRequest;
import com.gordeev.HRM.dictionary.dto.response.employmentMode.EmploymentModeResponse;
import com.gordeev.HRM.dictionary.service.EmploymentModeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employment-modes")
@RequiredArgsConstructor
public class EmploymentModeController {
    private final EmploymentModeService employmentModeService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<EmploymentModeResponse>>> createEmploymentModes(@RequestBody @Valid EmploymentModesCreateRequest request) {
        List<EmploymentModeResponse> result = employmentModeService.createEmploymentModes(request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmploymentModeResponse>>> getEmploymentModes() {
        List<EmploymentModeResponse> result = employmentModeService.getEmploymentModes();

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Void>> deleteEmploymentMode(@PathVariable String code) {
        employmentModeService.deleteEmploymentMode(code);

        return ResponseEntity.noContent().build();
    }

}
