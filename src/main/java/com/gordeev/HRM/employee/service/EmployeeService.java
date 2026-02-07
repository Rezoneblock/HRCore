package com.gordeev.HRM.employee.service;

import com.gordeev.HRM.common.exception.ResourceAlreadyExistsException;
import com.gordeev.HRM.employee.dto.CreateEmployeeRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.entity.Employee;
import com.gordeev.HRM.employee.mapper.EmployeeMapper;
import com.gordeev.HRM.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer;

    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);

        if (employeeRepository.existsByPersonalData_PassportSeriesAndPersonalData_PassportNumber(employee.getPersonalData().getPassportSeries(), employee.getPersonalData().getPassportNumber())) {
            throw new ResourceAlreadyExistsException("Employee with passport SERIAL and NUMBER: '" + employee.getPersonalData().getPassportSeries() + " " + employee.getPersonalData().getPassportNumber() + "' already exists");
        }

        if (employee.getPersonalData() != null) {
            employee.getPersonalData().setEmployee(employee);
        }
        if (employee.getContacts() != null) {
            employee.getContacts().setEmployee(employee);
        }
        if (employee.getEmploymentDetails() != null) {
            employee.getEmploymentDetails().setEmployee(employee);
        }

        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toResponse(saved);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    public Page<EmployeeResponse> searchEmployees(String fullName, Pageable pageable) {
        Page<Employee> page;

        if (fullName != null && !fullName.trim().isEmpty()) {
            page = employeeRepository.findByFullName(fullName.trim(), pageable);
        } else {
            page = employeeRepository.findAll(pageable);
        }

        return page.map(employeeMapper::toResponse);
    }

}
