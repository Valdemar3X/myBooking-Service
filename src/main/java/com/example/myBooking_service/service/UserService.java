package com.example.myBooking_service.service;

import com.example.myBooking_service.dto.user.UserRegistrationRequestDto;
import com.example.myBooking_service.dto.user.UserRegistrationResponseDto;
import com.example.myBooking_service.dto.user.UserUpdateInformationRequestDto;
import com.example.myBooking_service.dto.user.UserUpdateRoleRequestDto;
import com.example.myBooking_service.exception.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    UserRegistrationResponseDto updateRoleByUserId(Long id, UserUpdateRoleRequestDto requestDto);
    UserRegistrationResponseDto getCurrentUser(Long id);
    UserRegistrationResponseDto updateInformationAboutUser(Long id, UserUpdateInformationRequestDto requestDto);
}
