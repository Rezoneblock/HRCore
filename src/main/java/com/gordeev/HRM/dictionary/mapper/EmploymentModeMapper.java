package com.gordeev.HRM.dictionary.mapper;

import com.gordeev.HRM.dictionary.dto.response.employmentMode.EmploymentModeResponse;
import com.gordeev.HRM.dictionary.entity.EmploymentMode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmploymentModeMapper {
    EmploymentModeResponse toResponse(EmploymentMode employmentMode);
}
