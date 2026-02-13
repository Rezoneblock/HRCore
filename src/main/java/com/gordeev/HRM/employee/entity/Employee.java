package com.gordeev.HRM.employee.entity;

import com.gordeev.HRM.common.enums.EmployeeStatus;
import com.gordeev.HRM.onboarding.entity.OnboardingTask;
import com.gordeev.HRM.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OnboardingTask> onboardingTasks = new ArrayList<>();

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeePersonalData personalData;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeContacts contacts;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmploymentDetails employmentDetails;

    @OneToOne(mappedBy = "employee")
    private User user;


    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            user.setEmployee(this);
        }
    }

    public void setPersonalData(EmployeePersonalData data) {
        this.personalData = data;
        if (data != null) {
            data.setEmployee(this);
        }
    }

    public void setContacts(EmployeeContacts contacts) {
        this.contacts = contacts;
        if (contacts != null) {
            contacts.setEmployee(this);
        }
    }

    public void setEmploymentDetails (EmploymentDetails details) {
        this.employmentDetails = details;
        if (details != null) {
            details.setEmployee(this);
        }
    }

    public void addOnboardingTask(OnboardingTask task) {
        this.onboardingTasks.add(task);
        if (task != null) {
            task.setEmployee(this);
        }
    }

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
