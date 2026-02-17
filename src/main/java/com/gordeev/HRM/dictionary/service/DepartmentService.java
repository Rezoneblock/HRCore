package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.dictionary.dto.request.DepartmentCreateRequest;
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
    public DepartmentResponse createDepartment(DepartmentCreateRequest request) {
        Department department = departmentMapper.toDepartment(request);

        Department saved = departmentRepository.save(department);

        return departmentMapper.toResponse(saved);
    }
}
