package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
