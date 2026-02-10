package com.gordeev.HRM.employee.repository;

import com.gordeev.HRM.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Boolean existsByPersonalData_PassportSeriesAndPersonalData_PassportNumber(String passportSeries, String passportNumber);

    @Query("""
        select e from Employee e
        where lower(e.personalData.fullName) like lower(concat('%', :fullName, '%'))
""")
    Page<Employee> findByFullName(@Param("fullName") String fullName, Pageable pageable);
}
