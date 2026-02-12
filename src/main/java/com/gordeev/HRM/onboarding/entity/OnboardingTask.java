package com.gordeev.HRM.onboarding.entity;

import com.gordeev.HRM.common.enums.Departments;
import com.gordeev.HRM.common.enums.OnboardingStatus;
import com.gordeev.HRM.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "onboarding_tasks")
@Getter
@Setter
public class OnboardingTask {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Departments department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnboardingStatus status = OnboardingStatus.PENDGING;

    @Column(columnDefinition = "text")
    private String taskData;

    @Column(length = 500)
    private String comments;

    private LocalDateTime assignedAt = LocalDateTime.now();

    private LocalDateTime completedAt;

    @Column(length = 100)
    private String assignedTo;
}
