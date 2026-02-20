package com.gordeev.HRM.dictionary.mapper;

import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentCreateRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentPatchRequest;
import com.gordeev.HRM.dictionary.dto.response.department.DepartmentResponse;
import com.gordeev.HRM.dictionary.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
    DepartmentResponse toResponse(Department department);

    Department toDepartment(DepartmentCreateRequest request);

    void patchFromDepartmentRequest(DepartmentPatchRequest request, @MappingTarget Department department);
}
