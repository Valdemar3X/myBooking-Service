package com.example.myBooking_service.service;

import com.example.myBooking_service.dto.user.RegistrationUserRequestDto;
import com.example.myBooking_service.dto.user.RegistrationUserResponseDto;
import com.example.myBooking_service.exception.RegistrationException;

public interface UserService {
    RegistrationUserResponseDto register(RegistrationUserRequestDto requestDto) throws RegistrationException;
}
