package com.gordeev.HRM.onboarding.repository;

import com.gordeev.HRM.common.enums.OnboardingStatus;
import com.gordeev.HRM.onboarding.entity.OnboardingTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OnboardingTaskRepository extends JpaRepository<OnboardingTask, UUID> {
    List<OnboardingTask> findByEmployeeId(UUID id);
}
