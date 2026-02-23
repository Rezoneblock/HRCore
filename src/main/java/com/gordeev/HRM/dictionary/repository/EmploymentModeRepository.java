package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.entity.EmploymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentModeRepository extends JpaRepository<EmploymentMode, Long> {
    Boolean existsByCode(String code);
}
