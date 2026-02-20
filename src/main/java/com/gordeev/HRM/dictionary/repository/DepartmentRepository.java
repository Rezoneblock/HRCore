package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.dto.request.departments.SetOnboardingDepartmentsRequest;
import com.gordeev.HRM.dictionary.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByCodeIn(List<String> codes);
    Boolean existsByCode(String code);
}
