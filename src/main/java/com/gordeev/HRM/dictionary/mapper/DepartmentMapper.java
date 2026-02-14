package com.gordeev.HRM.dictionary.mapper;

import com.gordeev.HRM.dictionary.dto.response.DepartmentResponse;
import com.gordeev.HRM.dictionary.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
    DepartmentResponse toResponse(Department department);
}
