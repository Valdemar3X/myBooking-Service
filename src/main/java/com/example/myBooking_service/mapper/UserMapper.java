package com.example.myBooking_service.mapper;

import com.example.myBooking_service.config.MapperConfig;
import com.example.myBooking_service.dto.user.UserRegistrationRequestDto;
import com.example.myBooking_service.dto.user.UserRegistrationResponseDto;
import com.example.myBooking_service.model.User;
import org.mapstruct.Mapper;


@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserRegistrationResponseDto toUserResponse(User savedUser);

    User toModel(UserRegistrationRequestDto requestDto);
}