package com.gordeev.HRM.employee.service;

import com.gordeev.HRM.employee.dto.CreateEmployeeRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.entity.Employee;
import com.gordeev.HRM.employee.mapper.EmployeeMapper;
import com.gordeev.HRM.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);

        if (employee.getPersonalData() != null) {
            employee.getPersonalData().setEmployee(employee);
        }
        if (employee.getContacts() != null) {
            employee.getContacts().setEmployee(employee);
        }
        if (employee.getEmploymentDetails() != null) {
            employee.getEmploymentDetails().setEmployee(employee);
        }

        System.out.println(employee.getStatus());

        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toResponse(saved);
    }
}
