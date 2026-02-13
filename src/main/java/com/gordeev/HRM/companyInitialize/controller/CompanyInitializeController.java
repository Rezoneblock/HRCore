package com.gordeev.HRM.companyInitialize.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.employee.dto.EmployeeCreateRequest;
import com.gordeev.HRM.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CompanyInitializeController {

    @PostMapping
    public ResponseEntity<List<ApiResponse<UserResponse>>> createUser(@RequestBody List<EmployeeCreateRequest> employees) {

    }

    //is-system=true ADMIN / SUPER_ADMIN / HR_MANAGER / EMPLOYEE / MANAGER
}
