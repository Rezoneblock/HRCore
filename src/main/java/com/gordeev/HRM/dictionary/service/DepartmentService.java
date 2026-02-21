package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.common.exception.ResourceAlreadyExistsException;
import com.gordeev.HRM.common.exception.ResourceDoesNotExistException;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentCreateRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentPatchRequest;
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
    private final RoleService roleService;

    public Page<DepartmentResponse> searchDepartments(String code, Pageable pageable) {
        Page<Department> page;

        if (code != null && !code.trim().isEmpty()) {
            page = departmentRepository.findByCode(code, pageable);
        } else {
            page = departmentRepository.findAll(pageable);
        }

        return page.map(departmentMapper::toResponse);
    }

    @Transactional
    public List<DepartmentResponse> createDepartment(List<DepartmentCreateRequest> request) {
        List<Department> departments = request.stream().map(departmentMapper::toDepartment).toList();

            departments.forEach(depart -> {
                if (departmentRepository.existsByCode(depart.getCode())) {
                    throw new ResourceAlreadyExistsException("Department with code: " + depart.getCode() + " already exists");
                }
                roleService.createRoles(depart);
            });

        List<Department> saved = departments.stream().map(departmentRepository::save).toList();

        return saved.stream().map(departmentMapper::toResponse).toList();
    }

    @Transactional
    public List<DepartmentResponse> setOnboardingDepartments(List<SetOnboardingDepartmentsRequest> request) {
        List<String> codes = request.stream().map(SetOnboardingDepartmentsRequest::codes).flatMap(List::stream).toList();

        List<Department> selectedDepartments = departmentRepository.findByCodeIn(codes);

        selectedDepartments.forEach(department -> department.setOnboarding(true));

        roleService.checkForAdmin();

        return selectedDepartments.stream().map(departmentMapper::toResponse).toList();
    }

    @Transactional
    public DepartmentResponse patchDepartment(Long id, DepartmentPatchRequest request) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceDoesNotExistException("Department with id " + id + " does not exist"));

        departmentMapper.patchFromDepartmentRequest(request, department);

        Department saved = departmentRepository.save(department);

        return departmentMapper.toResponse(saved);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceDoesNotExistException("Department with id " + id + " does not exist"));

        roleService.deleteRoles(department.getCode());

        departmentRepository.delete(department);
    }
}
