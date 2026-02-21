package com.gordeev.HRM.dictionary.mapper;

import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentCreateRequest;
import com.gordeev.HRM.dictionary.dto.request.departments.DepartmentPatchRequest;
import com.gordeev.HRM.dictionary.dto.response.department.DepartmentResponse;
import com.gordeev.HRM.dictionary.entity.Department;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
    DepartmentResponse toResponse(Department department);

    @Mapping(target = "code", source = "code", qualifiedByName = "toLowerCase")
    Department toDepartment(DepartmentCreateRequest request);

    @Mapping(target = "code", source = "code", qualifiedByName = "toLowerCase")
    void patchFromDepartmentRequest(DepartmentPatchRequest request, @MappingTarget Department department);

    @Named("toLowerCase")
    default String toLowerCase(String value) {
        return value != null ? value.toLowerCase() : null;
    }
}
