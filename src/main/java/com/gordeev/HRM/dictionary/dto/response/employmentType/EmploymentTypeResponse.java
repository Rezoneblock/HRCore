package com.gordeev.HRM.dictionary.dto.response.employmentType;

import lombok.Builder;

@Builder
public record EmploymentTypeResponse (
        String code, // REMOTE, OFFICE, HYBRID, ...
        Boolean active
) {
}
