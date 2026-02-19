package com.gordeev.HRM.dictionary.dto.request.departments;

public record DepartmentCreateRequest(

 String code, // IT, FINANCE, HR, ...

 String name, // Администрирование информационных систем, отдел кадров, ...

 String description
) {
}
