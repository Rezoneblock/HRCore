package com.gordeev.HRM.dictionary.dto.response.department;

import com.gordeev.HRM.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponse {
    private Long id;

    private String code; // IT, FINANCE, HR, ...

    private String name; // Администрирование информационных систем, отдел кадров, ...

    private boolean active;

    private User head;
}
