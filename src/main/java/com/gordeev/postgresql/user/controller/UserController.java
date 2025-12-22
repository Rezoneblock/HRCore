package com.gordeev.postgresql.user.controller;

import com.gordeev.postgresql.common.dto.ApiResponse;
import com.gordeev.postgresql.user.dto.request.UserCreateRequest;
import com.gordeev.postgresql.user.dto.response.UserResponse;
import com.gordeev.postgresql.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PersistenceContext
    private final EntityManager entityManager;

    // GET Users via pageable
    @GetMapping
    public ResponseEntity<@NonNull ApiResponse<@NonNull Page<@NonNull UserResponse>>> getUsers(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<@NonNull UserResponse> page = userService.getUsersPageable(pageable);
        ApiResponse<Page<@NonNull UserResponse>> response = ApiResponse.success(page);

        return ResponseEntity.ok(response);
    }

    // GET User via email
    @GetMapping("/by-email")
    public ResponseEntity<@NonNull ApiResponse<UserResponse>> getUserByEmail(@RequestParam String email) {
        UserResponse user = userService.findByEmail(email);
        ApiResponse<UserResponse> response = ApiResponse.success(user);

        return ResponseEntity.ok(response);
    }

    // GET all Users
    @GetMapping("/all")
    public ResponseEntity<@NonNull ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> allUsers = userService.getAllUsers();
        ApiResponse<List<UserResponse>> response = ApiResponse.success(allUsers);

        return ResponseEntity.ok(response);
    }

    // Create User
    @PostMapping
    public ResponseEntity<@NonNull ApiResponse<UserResponse>> createUser(@RequestBody UserCreateRequest request) {
        UserResponse newUser = userService.createUser(request);
        ApiResponse<UserResponse> response = ApiResponse.success(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Delete User via email
    @DeleteMapping("/by-email")
    public ResponseEntity<@NonNull ApiResponse<Void>> deleteUser(@RequestParam String email) {
        userService.deleteByEmail(email);

        return ResponseEntity.noContent().build();
    }

}
