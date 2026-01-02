package com.gordeev.HRM.employee.entity;

import com.gordeev.HRM.common.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status;

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

    public void setDetails (EmploymentDetails details) {
        this.employmentDetails = details;
        if (details != null) {
            employmentDetails.setEmployee(this);
        }
    }
}
