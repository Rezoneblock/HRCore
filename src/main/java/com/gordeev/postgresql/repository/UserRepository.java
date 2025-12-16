package com.gordeev.postgresql.repository;

import com.gordeev.postgresql.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<@NonNull User, @NonNull Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
