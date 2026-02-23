package com.gordeev.HRM.dictionary.mapper;

import com.gordeev.HRM.dictionary.dto.response.employmentType.EmploymentTypeResponse;
import com.gordeev.HRM.dictionary.entity.EmploymentType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmploymentTypeMapper {
    EmploymentTypeResponse toResponse(EmploymentType employmentType);
}
