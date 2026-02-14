package com.gordeev.HRM.onboarding.entity;

import com.gordeev.HRM.common.enums.OnboardingStatus;
import com.gordeev.HRM.employee.entity.Employee;
import com.gordeev.HRM.user.entity.User;
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

    @Column(nullable = false)
    private String department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnboardingStatus status = OnboardingStatus.PENDGING;

    @Column(columnDefinition = "text")
    private String taskData;

    @Column(length = 500)
    private String comment;

    private LocalDateTime assignedAt = LocalDateTime.now();

    private LocalDateTime completedAt;
}
