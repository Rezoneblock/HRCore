package com.gordeev.HRM.user.service;

import com.gordeev.HRM.user.dto.request.UserCreateRequest;
import com.gordeev.HRM.user.dto.request.UserUpdateRequest;
import com.gordeev.HRM.user.dto.response.UserResponse;
import com.gordeev.HRM.user.entity.User;
import com.gordeev.HRM.common.exception.ResourceAlreadyExistsException;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
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

        return page.map(userMapper::toResponse);
    }

    public UserResponse findByLogin(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " does not exists"));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        User user = userMapper.toUser(request);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public UserResponse partialUpdateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " does not exists"));

        // Если есть замена имени
        if (request.login() != null) {
            // Проверка на совпадение со старой почтой (нельзя)
            if (request.login().equals(user.getLogin())) {
                throw new ResourceAlreadyExistsException("New login can't be same as old one");
            } else { // Проверка на уникальность новой почты
                if (userRepository.existsByUsername(request.login())) {
                    throw new ResourceAlreadyExistsException("User with login: '" + request.login() + "' already exists");
                }
            }
        }

        userMapper.updateUserFromRequest(request, user);

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }
}
