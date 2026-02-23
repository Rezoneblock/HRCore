package com.gordeev.HRM.dictionary.dto.response.employmentMode;

import lombok.Builder;

@Builder
public record EmploymentModeResponse(
        String code, // REMOTE, OFFICE, HYBRID, ...
        String name, // Удаленно, в офисе, гибрид, ...
        boolean active
) {
}
