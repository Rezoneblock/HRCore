package com.gordeev.HRM.dictionary.dto.request.employmentMode;

import java.util.List;

public record EmploymentModesCreateRequest(
        List<String> codes
) {
}
