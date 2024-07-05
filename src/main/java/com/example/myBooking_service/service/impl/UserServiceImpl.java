package com.example.myBooking_service.service.impl;

import com.example.myBooking_service.dto.user.RegistrationUserRequestDto;
import com.example.myBooking_service.dto.user.RegistrationUserResponseDto;
import com.example.myBooking_service.exception.RegistrationException;
import com.example.myBooking_service.mapper.UserMapper;
import com.example.myBooking_service.model.User;
import com.example.myBooking_service.repository.UserRepository;
import com.example.myBooking_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public RegistrationUserResponseDto register(RegistrationUserRequestDto requestDto) throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("Unable to complete registration.");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoleName((User.RoleName.CUSTOMER));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
