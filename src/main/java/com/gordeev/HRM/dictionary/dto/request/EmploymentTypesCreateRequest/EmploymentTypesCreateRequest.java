package com.gordeev.HRM.dictionary.dto.request.EmploymentTypesCreateRequest;

import java.util.List;

public record EmploymentTypesCreateRequest(
        List<String> codes
) {
}
