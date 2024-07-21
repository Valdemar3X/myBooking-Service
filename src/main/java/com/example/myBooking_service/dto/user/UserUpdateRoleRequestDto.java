package com.example.myBooking_service.dto.user;

import com.example.myBooking_service.model.User;

public record UserUpdateRoleRequestDto(User.RoleName roleName) {
}
