package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.entity.OnboardingDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingDepartmentRepository extends JpaRepository<OnboardingDepartment, Long> {
}
