package com.gordeev.postgresql.service;

import com.gordeev.postgresql.dto.request.UserCreateRequest;
import com.gordeev.postgresql.dto.response.UserResponse;
import com.gordeev.postgresql.entity.User;
import com.gordeev.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getFirstname(),
                savedUser.getLastname(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }
}
