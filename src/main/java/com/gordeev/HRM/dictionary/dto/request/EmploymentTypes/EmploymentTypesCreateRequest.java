package com.gordeev.HRM.dictionary.dto.request.EmploymentTypes;

import java.util.List;

public record EmploymentTypesCreateRequest(
        List<String> codes
) {
}
