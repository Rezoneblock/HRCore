package com.gordeev.HRM.dictionary.service;

import com.gordeev.HRM.dictionary.entity.Department;
import com.gordeev.HRM.dictionary.entity.Role;
import com.gordeev.HRM.dictionary.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void createRoles(Department department) {
        Role role_employee = Role.builder()
                .code(department.getCode() + "_EMPLOYEE")
                .department(department.getCode())
                .name("Сотрудник отдела " + department.getCode()).build();

        Role role_head = Role.builder()
                .code(department.getCode() + "_HEAD")
                .department(department.getCode())
                .name("Начальник отдела " + department.getCode()).build();

        roleRepository.saveAll(List.of(role_employee, role_head));
    }

    public void checkForAdmin() {
        if (!roleRepository.existsByCode("ADMIN")) {
            Role role_admin = Role.builder()
                    .code("ADMIN")
                    .name("Администратор")
                    .department("IT")
                    .isSystem(true).build();

            roleRepository.save(role_admin);
        }
    }

    @Transactional
    public void deleteRoles(String code) {
        roleRepository.deleteByDepartment(code);
    }
}
