package com.gordeev.HRCore.user.controller;

import com.gordeev.HRCore.common.dto.ApiResponse;
import com.gordeev.HRCore.user.dto.request.UserCreateRequest;
import com.gordeev.HRCore.user.dto.request.UserUpdateRequest;
import com.gordeev.HRCore.user.dto.response.UserResponse;
import com.gordeev.HRCore.user.service.UserService;
import jakarta.validation.Valid;
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

    // GET Users via pageable
    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getUsers(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<UserResponse> page = userService.getUsersPageable(pageable);
        ApiResponse<Page<UserResponse>> response = ApiResponse.success(page);

        return ResponseEntity.ok(response);
    }

    // GET User via email
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(@PathVariable String username) {
        UserResponse user = userService.findByUsername(username);
        ApiResponse<UserResponse> response = ApiResponse.success(user);

        return ResponseEntity.ok(response);
    }

    // GET all Users
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> allUsers = userService.getAllUsers();
        ApiResponse<List<UserResponse>> response = ApiResponse.success(allUsers);

        return ResponseEntity.ok(response);
    }

    // Create User
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserCreateRequest request) {
        UserResponse newUser = userService.createUser(request);
        ApiResponse<UserResponse> response = ApiResponse.success(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Delete User via email
    @DeleteMapping("/by-email")
    public ResponseEntity<Void> deleteUser(@RequestParam String email) {
        userService.deleteByEmail(email);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest request) {
        UserResponse user = userService.partialUpdateUser(id, request);

        ApiResponse<UserResponse> response = ApiResponse.success(user);

        return ResponseEntity.ok(response);
    }

}
