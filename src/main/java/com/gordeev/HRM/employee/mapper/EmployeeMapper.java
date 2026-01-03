package com.gordeev.HRM.employee.mapper;

import com.gordeev.HRM.common.enums.EmployeeStatus;
import com.gordeev.HRM.employee.dto.CreateEmployeeRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.entity.Employee;
import com.gordeev.HRM.employee.entity.EmployeeContacts;
import com.gordeev.HRM.employee.entity.EmployeePersonalData;
import com.gordeev.HRM.employee.entity.EmploymentDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "status", constant = "ACTIVE")
    Employee toEmployee(CreateEmployeeRequest request);
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "status", constant = "ACTIVE")
//    @Mapping(target = "personalData", source = "request")
//    @Mapping(target = "contacts", source = "request")
//    @Mapping(target = "employmentDetails", source = "request")
//    Employee toEmployee(CreateEmployeeRequest request);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "employee", ignore = true)
//    @Mapping(target = "fullName", source = "fullName")
//    EmployeePersonalData toPersonalData(CreateEmployeeRequest request);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "employee", ignore = true)
//    @Mapping(target = "email", source = "email")
//    EmployeeContacts toContacts(CreateEmployeeRequest request);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "employee", ignore = true)
//    @Mapping(target = "department", source = "department")
//    EmploymentDetails toEmploymentDetails(CreateEmployeeRequest request);

    EmployeeResponse toResponse(Employee employee);
}
