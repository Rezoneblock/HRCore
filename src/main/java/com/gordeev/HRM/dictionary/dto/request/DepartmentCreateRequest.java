package com.gordeev.HRM.dictionary.dto.request;

public record DepartmentCreateRequest(

 String code, // IT, FINANCE, HR, ...

 String name, // Администрирование информационных систем, отдел кадров, ...

 String description
) {
}
