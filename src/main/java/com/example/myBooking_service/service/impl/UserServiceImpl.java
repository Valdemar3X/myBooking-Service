package com.example.myBooking_service.service.impl;

import com.example.myBooking_service.dto.user.UserRegistrationRequestDto;
import com.example.myBooking_service.dto.user.UserRegistrationResponseDto;
import com.example.myBooking_service.dto.user.UserUpdateInformationRequestDto;
import com.example.myBooking_service.dto.user.UserUpdateRoleRequestDto;
import com.example.myBooking_service.exception.RegistrationException;
import com.example.myBooking_service.mapper.UserMapper;
import com.example.myBooking_service.model.User;
import com.example.myBooking_service.repository.UserRepository;
import com.example.myBooking_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("Unable to complete registration.");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoleName((User.RoleName.CUSTOMER));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
    @Transactional
    @Override
    public UserRegistrationResponseDto updateRoleByUserId(Long id, UserUpdateRoleRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find entity by id: " + id));
        user.setRoleName(requestDto.roleName());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserRegistrationResponseDto getCurrentUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("the user is not authenticated")
        );
        return userMapper.toUserResponse(user);
    }

    @Transactional
    @Override
    public UserRegistrationResponseDto updateInformationAboutUser(
            Long id, UserUpdateInformationRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("the user is not authenticated")
        );
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
