package com.gordeev.HRM.user.service;

import com.gordeev.HRM.user.dto.request.UserCreateRequest;
import com.gordeev.HRM.user.dto.request.UserUpdateRequest;
import com.gordeev.HRM.user.dto.response.UserResponse;
import com.gordeev.HRM.user.entity.User;
import com.gordeev.HRM.user.exception.ResourceAlreadyExistsException;
import com.gordeev.HRM.user.exception.UserNotFoundException;
import com.gordeev.HRM.user.exception.UsersPageEmptyException;
import com.gordeev.HRM.user.mapper.UserMapper;
import com.gordeev.HRM.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.RequestContextFilter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final RequestContextFilter requestContextFilter;

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

    public UserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " does not exists"));
        return userToResponse(user);
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("User with email: '" + request.email() + "' already exists");
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

    @Transactional
    public UserResponse partialUpdateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " does not exists"));

        // Если есть замена почты
        if (request.email() != null) {
            // Проверка на совпадение со старой почтой (нельзя)
            if (request.email().equals(user.getEmail())) {
                throw new ResourceAlreadyExistsException("New email can't be same as old one");
            } else { // Проверка на уникальность новой почты
                if (userRepository.existsByEmail(request.email())) {
                    throw new ResourceAlreadyExistsException("User with email: '" + request.email() + "' already exists");
                }
            }
        }

        // Если есть замена почты
        if (request.username() != null) {
            // Проверка на совпадение со старой почтой (нельзя)
            if (request.username().equals(user.getUsername())) {
                throw new ResourceAlreadyExistsException("New username can't be same as old one");
            } else { // Проверка на уникальность новой почты
                if (userRepository.existsByUsername(request.username())) {
                    throw new ResourceAlreadyExistsException("User with username: '" + request.username() + "' already exists");
                }
            }
        }

        userMapper.updateUserFromRequest(request, user);

        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }
}
