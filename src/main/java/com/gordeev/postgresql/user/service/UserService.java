package com.gordeev.postgresql.user.service;

import com.gordeev.postgresql.user.dto.request.UserCreateRequest;
import com.gordeev.postgresql.user.dto.response.UserResponse;
import com.gordeev.postgresql.user.entity.User;
import com.gordeev.postgresql.common.exception.EmailAlreadyExistsException;
import com.gordeev.postgresql.common.exception.NotFoundException;
import com.gordeev.postgresql.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " does not exist"));
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " does not exist"));
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
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
