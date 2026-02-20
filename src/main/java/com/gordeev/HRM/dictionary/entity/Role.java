package com.gordeev.HRM.dictionary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dict_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String code; // ADMIN, HR, IT_HEAD, IT_EMPLOYEE, ...

    @Column(nullable = false, length = 200)
    private String name; // Админ, кадровик, глава IT отдела, сотрудник IT отдела, ...

    private boolean isSystem = false;
}
