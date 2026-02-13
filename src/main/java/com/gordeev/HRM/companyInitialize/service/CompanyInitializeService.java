package com.gordeev.HRM.companyInitialize.service;

import com.gordeev.HRM.employee.dto.EmployeeCreateRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyInitializeService {
    public List<EmployeeResponse> createEmployees(List<EmployeeCreateRequest> employees) {
        if (employees == null || employees.isEmpty()) {
            return Collections.emptyList();
        }

        List<>
    }
}
