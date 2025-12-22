package com.gordeev.postgresql.user.service;

import com.gordeev.postgresql.user.dto.request.UserCreateRequest;
import com.gordeev.postgresql.user.dto.response.UserResponse;
import com.gordeev.postgresql.user.entity.User;
import com.gordeev.postgresql.user.exception.EmailAlreadyExistsException;
import com.gordeev.postgresql.user.exception.UserNotFoundException;
import com.gordeev.postgresql.user.exception.UsersPageEmptyException;
import com.gordeev.postgresql.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private UserResponse userToResponse(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::userToResponse)
                .collect(Collectors.toList());
    }

    public Page<@NonNull UserResponse> getUsersPageable(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable не может быть null");
        }

        Page<@NonNull User> page = userRepository.findAll(pageable);

        if (page.isEmpty()) {
            throw new UsersPageEmptyException("page=" + pageable.getPageNumber() + " and size=" + pageable.getPageSize() + " does not exists");
        }

        return page.map(this::userToResponse);
    }


    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exists"));
        return userToResponse(user);
    }

    public UserResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " does not exists"));
        return userToResponse(user);
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("User with email: '" + request.email() + "' already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .build();

        User savedUser = userRepository.save(user);

        return userToResponse(savedUser);
    }

    @Transactional
    public void deleteByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " does not exists"));

        userRepository.delete(user);
    }
}
