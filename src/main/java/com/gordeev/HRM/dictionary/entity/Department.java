package com.gordeev.HRM.dictionary.entity;

import com.gordeev.HRM.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "dict_departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code; // IT, FINANCE, HR, ...

    @Column(nullable = false, length = 200)
    private String name; // Администрирование информационных систем, отдел кадров, ...

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private Boolean isOnboarding = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_user_id")
    private User head;

}
