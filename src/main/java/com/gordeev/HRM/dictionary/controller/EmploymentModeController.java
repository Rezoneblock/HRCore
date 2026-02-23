package com.gordeev.HRM.dictionary.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.dictionary.dto.response.employmentMode.EmploymentModeResponse;
import com.gordeev.HRM.dictionary.service.EmploymentModeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employment-modes")
@RequiredArgsConstructor
public class EmploymentModeController {
    private final EmploymentModeService employmentModeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmploymentModeResponse>> createEmploymentModes() {

    }
}
