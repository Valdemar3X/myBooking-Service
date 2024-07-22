package com.example.mybookingservice.service;

import com.example.mybookingservice.dto.user.UserRegistrationRequestDto;
import com.example.mybookingservice.dto.user.UserRegistrationResponseDto;
import com.example.mybookingservice.dto.user.UserUpdateInformationRequestDto;
import com.example.mybookingservice.dto.user.UserUpdateRoleRequestDto;
import com.example.mybookingservice.exception.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    UserRegistrationResponseDto updateRoleByUserId(Long id,
                                                   UserUpdateRoleRequestDto requestDto);

    UserRegistrationResponseDto getCurrentUser(Long id);

    UserRegistrationResponseDto updateInformationAboutUser(
            Long id, UserUpdateInformationRequestDto requestDto);
}
