package com.gordeev.postgresql.user.controller;

import com.gordeev.postgresql.common.dto.ApiResponse;
import com.gordeev.postgresql.user.dto.request.UserCreateRequest;
import com.gordeev.postgresql.user.dto.response.UserResponse;
import com.gordeev.postgresql.user.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/by-email")
    public ResponseEntity<@NonNull ApiResponse<UserResponse>> getUserByEmail(@RequestParam String email) {
        UserResponse user = userService.findByEmail(email);
        ApiResponse<UserResponse> response = ApiResponse.success(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<@NonNull ApiResponse<UserResponse>> createUser(@RequestBody UserCreateRequest request) {
        UserResponse newUser = userService.createUser(request);
        ApiResponse<UserResponse> response = ApiResponse.success(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
