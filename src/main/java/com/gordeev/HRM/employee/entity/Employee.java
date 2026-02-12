package com.gordeev.HRM.employee.entity;

import com.gordeev.HRM.common.enums.EmployeeStatus;
import com.gordeev.HRM.onboarding.entity.OnboardingTask;
import jakarta.persistence.*;
import lombok.*;

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


    public void setPersonalData(EmployeePersonalData data) {
        this.personalData = data;
        if (data != null) {
            personalData.setEmployee(this);
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
            employmentDetails.setEmployee(this);
        }
    }

    public void addOnboardingTask(OnboardingTask task) {
        this.onboardingTasks.add(task);
        if (task != null) {
            task.setEmployee(this);
        }
    }
}
