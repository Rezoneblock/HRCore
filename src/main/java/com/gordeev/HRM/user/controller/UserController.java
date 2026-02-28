package com.gordeev.HRM.user.controller;

import com.gordeev.HRM.common.dto.ApiResponse;
import com.gordeev.HRM.user.dto.request.UserCreateRequest;
import com.gordeev.HRM.user.dto.request.UserUpdateRequest;
import com.gordeev.HRM.user.dto.response.UserResponse;
import com.gordeev.HRM.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // GET Users via pageable
    @GetMapping
    public ResponseEntity<ApiResponse<PagedModel<UserResponse>>> getUsers(
            @RequestParam String login,
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<UserResponse> page = userService.searchUsers(login, pageable);

        PagedModel<UserResponse> pagedModel = PagedModel.of(
                page.getContent(),
                new PagedModel.PageMetadata(
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalElements(),
                        page.getTotalPages()
                )
        );

        return ResponseEntity.ok(ApiResponse.success(pagedModel));
    }

    // Create User
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid UserCreateRequest request) {
        UserResponse newUser = userService.createUser(request, null);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(newUser));
    }

    // Patch User
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateRequest request) {
        UserResponse user = userService.partialUpdateUser(id, request);

        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}
