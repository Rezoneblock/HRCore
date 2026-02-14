package com.gordeev.HRM.dictionary.repository;

import com.gordeev.HRM.dictionary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
