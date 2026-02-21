package com.gordeev.HRM.dictionary.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentCreateRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentPatchRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.SetOnboardingDepartmentsRequest;
import com.gordeev.HRM.dictionary.dto.response.department.DepartmentResponse;
import com.gordeev.HRM.dictionary.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<PagedModel<DepartmentResponse>>> getAllDepartments(
            @RequestParam String code,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<DepartmentResponse> result = departmentService.searchDepartments(code, pageable);

        PagedModel<DepartmentResponse> pagedModel = PagedModel.of(
                result.getContent(),
                new PagedModel.PageMetadata(
                        result.getSize(),
                        result.getNumber(),
                        result.getTotalElements(),
                        result.getTotalPages()
                )
        );

        return ResponseEntity.ok(ApiResponse.success(pagedModel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> patchDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentPatchRequest request) {
        DepartmentResponse result = departmentService.patchDepartment(id, request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> createDepartment(@RequestBody List<@Valid DepartmentCreateRequest> request) {
        List<DepartmentResponse> result = departmentService.createDepartment(request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/set-onboarding")
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> setOnboardingDepartments(@RequestBody List<@Valid SetOnboardingDepartmentsRequest> request) {
        List<DepartmentResponse> result = departmentService.setOnboardingDepartments(request);

        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
