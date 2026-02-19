package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentCreateRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.SetOnboardingDepartmentsRequest;
import com.gordeev.HRM.dictionary.dto.response.DepartmentResponse;
import com.gordeev.HRM.dictionary.entity.Department;
import com.gordeev.HRM.dictionary.mapper.DepartmentMapper;
import com.gordeev.HRM.dictionary.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Transactional
    public List<DepartmentResponse> createDepartment(List<DepartmentCreateRequest> request) {
        List<Department> departments = request.stream().map(departmentMapper::toDepartment).toList();

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
