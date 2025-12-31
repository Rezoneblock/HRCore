package com.gordeev.HRM.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter  // Или @Builder + @NoArgsConstructor + @AllArgsConstructor для полного контроля
@NoArgsConstructor  // Обязательно — пустой конструктор для ручного создания
@AllArgsConstructor // Конструктор со всеми полями
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
