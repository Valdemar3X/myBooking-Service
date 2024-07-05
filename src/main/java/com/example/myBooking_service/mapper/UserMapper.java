package com.example.myBooking_service.mapper;

import com.example.myBooking_service.config.MapperConfig;
import com.example.myBooking_service.dto.user.RegistrationUserRequestDto;
import com.example.myBooking_service.dto.user.RegistrationUserResponseDto;
import com.example.myBooking_service.model.User;
import org.mapstruct.Mapper;


@Mapper(config = MapperConfig.class)
public interface UserMapper {
    RegistrationUserResponseDto toUserResponse(User savedUser);

    User toModel(RegistrationUserRequestDto requestDto);
}