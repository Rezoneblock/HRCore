package com.gordeev.HRM.user.repository;

import com.gordeev.HRM.employee.entity.Employee;
import com.gordeev.HRM.user.entity.User;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByLogin(String login);

    @Query("""
        select e from User e
        where lower(login) like lower(concat('%', :login, '%'))
""")
    Page<User> findByLogin(@Param("login") String login, Pageable pageable);
}
