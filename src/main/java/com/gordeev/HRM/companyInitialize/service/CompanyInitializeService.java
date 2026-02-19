package com.gordeev.HRM.companyInitialize.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyInitializeService {
//    public List<EmployeeResponse> createEmployees(List<EmployeeCreateRequest> employees) {
//        if (employees == null || employees.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//
//    }
}
