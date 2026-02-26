package com.gordeev.HRM.employee.service;

import com.gordeev.HRM.common.exception.ResourceAlreadyExistsException;
import com.gordeev.HRM.common.exception.ResourceDoesNotExistException;
import com.gordeev.HRM.dictionary.repository.DepartmentRepository;
import com.gordeev.HRM.dictionary.repository.EmploymentModeRepository;
import com.gordeev.HRM.dictionary.repository.EmploymentTypeRepository;
import com.gordeev.HRM.employee.dto.EmployeeCreateRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.dto.EmployeeUpdateRequest;
import com.gordeev.HRM.employee.entity.Employee;
import com.gordeev.HRM.employee.mapper.EmployeeMapper;
import com.gordeev.HRM.employee.repository.EmployeeRepository;
import com.gordeev.HRM.user.dto.request.UserCreateRequest;
import com.gordeev.HRM.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserService userService;
    private final DepartmentRepository departmentRepository;
    private final EmploymentTypeRepository employmentTypeRepository;
    private final EmploymentModeRepository employmentModeRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    @Qualifier("entityLinksPluginRegistry")
    private final OrderAwarePluginRegistry<EntityLinks, Class<?>> entityLinksPluginRegistry;

    @Transactional
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        Employee employee = employeeMapper.toEmployeeFromCreate(request);

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

        assert employee.getEmploymentDetails() != null;
        if (!departmentRepository.existsByCode(employee.getEmploymentDetails().getDepartment())) {
            throw new ResourceDoesNotExistException("Department with code " + departmentRepository.existsByCode(employee.getEmploymentDetails().getDepartment()) + " does not exist");
        }
        if (!employmentModeRepository.existsByCode(employee.getEmploymentDetails().getWorkFrom())) {
            throw new ResourceDoesNotExistException("WorkFrom with code " + employmentModeRepository.existsByCode(employee.getEmploymentDetails().getWorkFrom()) + " does not exist");
        }
        if (!employmentTypeRepository.existsByCode(employee.getEmploymentDetails().getEmploymentType())) {
            throw new ResourceDoesNotExistException("EmploymentType with code " + employmentTypeRepository.existsByCode(employee.getEmploymentDetails().getEmploymentType()) + " does not exist");
        }

        Employee saved = employeeRepository.save(employee);

        entityManager.flush();
        entityManager.refresh(saved);

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .login(saved.getContacts().getEmail())
                .fullName(saved.getPersonalData().getFullName())
                .build();

        userService.createUser(userCreateRequest, saved);

        return employeeMapper.toResponse(saved);
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

    @Transactional
    public EmployeeResponse partialEmployeeUpdate(UUID id, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceDoesNotExistException("Employee with id: " + id + " does not exist"));

        employeeMapper.toEmployeeFromUpdate(request, employee);

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceDoesNotExistException("Employee with id: " + id + " does not exist"));

        employeeRepository.delete(employee);
    }

}
