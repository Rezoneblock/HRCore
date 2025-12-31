package com.gordeev.HRM.user.mapper;

import com.gordeev.HRM.user.dto.request.UserUpdateRequest;
import com.gordeev.HRM.user.dto.response.UserResponse;
import com.gordeev.HRM.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    void updateUserFromRequest(UserUpdateRequest dto, @MappingTarget User user);

    UserResponse toDto(User user);
}
