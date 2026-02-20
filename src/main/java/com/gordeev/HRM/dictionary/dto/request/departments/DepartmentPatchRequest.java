package com.gordeev.HRM.dictionary.dto.request.departments;

import java.util.UUID;

public record DepartmentPatchRequest(
        String code,
        String name,
        boolean active,
        boolean isOnboarding,
        UUID headId // new head user id
) {
}
