package com.example.mybookingservice.mapper;

import com.example.mybookingservice.config.MapperConfig;
import com.example.mybookingservice.dto.user.UserRegistrationRequestDto;
import com.example.mybookingservice.dto.user.UserRegistrationResponseDto;
import com.example.mybookingservice.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserRegistrationResponseDto toUserResponse(User savedUser);

    User toModel(UserRegistrationRequestDto requestDto);
}
