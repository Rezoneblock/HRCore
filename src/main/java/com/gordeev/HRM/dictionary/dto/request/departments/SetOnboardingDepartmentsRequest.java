package com.gordeev.HRM.dictionary.dto.request.departments;

import java.util.List;

public record SetOnboardingDepartmentsRequest(
        List<String> codes
) {
}
