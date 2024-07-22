package com.example.mybookingservice.dto.user;

import com.example.mybookingservice.model.User;

public record UserUpdateRoleRequestDto(User.RoleName roleName) {
}
