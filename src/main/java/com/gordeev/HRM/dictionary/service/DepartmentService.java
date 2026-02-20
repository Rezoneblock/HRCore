package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.common.exception.ResourceAlreadyExistsException;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentCreateRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.SetOnboardingDepartmentsRequest;
import com.gordeev.HRM.dictionary.dto.response.department.DepartmentResponse;
import com.gordeev.HRM.dictionary.entity.Department;
import com.gordeev.HRM.dictionary.entity.Role;
import com.gordeev.HRM.dictionary.mapper.DepartmentMapper;
import com.gordeev.HRM.dictionary.repository.DepartmentRepository;
import com.gordeev.HRM.dictionary.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final RoleRepository roleRepository;

    public Page<DepartmentResponse> getAllDepartments(Pageable pageable) {
        Page<Department> page = departmentRepository.findAll(pageable);

        return page.map(departmentMapper::toResponse);
    }

    @Transactional
    public List<DepartmentResponse> createDepartment(List<DepartmentCreateRequest> request) {
        List<Department> departments = request.stream().map(departmentMapper::toDepartment).toList();

        if (!roleRepository.existsByCode("ADMIN")) {
            Role role_admin = Role.builder()
                    .code("ADMIN")
                    .name("Администратор")
                    .isSystem(true).build();

            roleRepository.save(role_admin);
        }
            departments.forEach(depart -> {
                if (departmentRepository.existsByCode(depart.getCode())) {
                    throw new ResourceAlreadyExistsException("Department with code: " + depart.getCode() + " already exists");
                }
                Role role_employee = Role.builder()
                        .code(depart.getCode() + "_EMPLOYEE")
                        .name("Сотрудник отдела " + depart.getCode()).build();

                Role role_head = Role.builder()
                        .code(depart.getCode() + "_HEAD")
                        .name("Начальник отдела " + depart.getCode()).build();

                roleRepository.saveAll(List.of(role_employee, role_head));
            });

        List<Department> saved = departments.stream().map(departmentRepository::save).toList();

        return saved.stream().map(departmentMapper::toResponse).toList();
    }

    public List<DepartmentResponse> setOnboardingDepartments(List<SetOnboardingDepartmentsRequest> request) {
        List<String> codes = request.stream().map(SetOnboardingDepartmentsRequest::codes).flatMap(List::stream).toList();

        List<Department> selectedDepartments = departmentRepository.findByCodeIn(codes);

        selectedDepartments.forEach(department -> department.setOnboarding(true));

        return selectedDepartments.stream().map(departmentMapper::toResponse).toList();
    }
}
