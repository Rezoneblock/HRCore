package com.gordeev.HRM.employee.mapper;

import com.gordeev.HRM.employee.dto.EmployeeCreateRequest;
import com.gordeev.HRM.employee.dto.EmployeeResponse;
import com.gordeev.HRM.employee.dto.EmployeeUpdateRequest;
import com.gordeev.HRM.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    // Personal data
    @Mapping(target = "personalData.fullName", source = "fullName")
    @Mapping(target = "personalData.birthDate", source = "birthDate")
    @Mapping(target = "personalData.sex", source = "sex")
    @Mapping(target = "personalData.passportSeries", source = "passportSeries")
    @Mapping(target = "personalData.passportNumber", source = "passportNumber")
    @Mapping(target = "personalData.address", source = "address")
    // Contacts
    @Mapping(target = "contacts.phone", source = "phone")
    @Mapping(target = "contacts.email", source = "email")
    // Details
    @Mapping(target = "employmentDetails.position", source = "position")
    @Mapping(target = "employmentDetails.department", source = "department")
    @Mapping(target = "employmentDetails.employmentType", source = "employmentType")
    @Mapping(target = "employmentDetails.workFrom", source = "workFrom")
    @Mapping(target = "employmentDetails.salary", source = "salary")
    @Mapping(target = "employmentDetails.status", source = "status")
    void toEmployeeFromUpdate(EmployeeUpdateRequest request, @MappingTarget Employee employee);

    @Mapping(target = "id", ignore = true)
    // Personal data
    @Mapping(target = "personalData.fullName", source = "fullName")
    @Mapping(target = "personalData.birthDate", source = "birthDate")
    @Mapping(target = "personalData.sex", source = "sex")
    @Mapping(target = "personalData.passportSeries", source = "passportSeries")
    @Mapping(target = "personalData.passportNumber", source = "passportNumber")
    @Mapping(target = "personalData.address", source = "address")
    // Contacts
    @Mapping(target = "contacts.phone", source = "phone")
    @Mapping(target = "contacts.email", source = "email")
    // Details
    @Mapping(target = "employmentDetails.hireDate", source = "hireDate")
    @Mapping(target = "employmentDetails.position", source = "position")
    @Mapping(target = "employmentDetails.department", source = "department")
    @Mapping(target = "employmentDetails.employmentType", source = "employmentType")
    @Mapping(target = "employmentDetails.workFrom", source = "workFrom")
    @Mapping(target = "employmentDetails.salary", source = "salary")
    @Mapping(target = "employmentDetails.status", constant = "ONBOARDING")
    Employee toEmployeeFromCreate(EmployeeCreateRequest request);

    @Mapping(target = "fullName", source = "personalData.fullName")
    @Mapping(target = "birthDate", source = "personalData.birthDate")
    @Mapping(target = "sex", source = "personalData.sex")
    // Contacts
    @Mapping(target = "phone", source = "contacts.phone")
    @Mapping(target = "email", source = "contacts.email")
    // Details
    @Mapping(target = "hireDate", source = "employmentDetails.hireDate")
    @Mapping(target = "position", source = "employmentDetails.position")
    @Mapping(target = "department", source = "employmentDetails.department")
    @Mapping(target = "salary", source = "employmentDetails.salary")
    @Mapping(target = "status", source = "employmentDetails.status")
    EmployeeResponse toResponse(Employee employee);
}
