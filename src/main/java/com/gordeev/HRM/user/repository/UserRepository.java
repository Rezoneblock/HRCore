package com.gordeev.HRM.user.repository;

import com.gordeev.HRM.user.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
}
