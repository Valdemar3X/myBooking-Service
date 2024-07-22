package com.example.mybookingservice.service.impl;

import com.example.mybookingservice.dto.user.UserRegistrationRequestDto;
import com.example.mybookingservice.dto.user.UserRegistrationResponseDto;
import com.example.mybookingservice.dto.user.UserUpdateInformationRequestDto;
import com.example.mybookingservice.dto.user.UserUpdateRoleRequestDto;
import com.example.mybookingservice.exception.EntityNotFoundException;
import com.example.mybookingservice.exception.RegistrationException;
import com.example.mybookingservice.mapper.UserMapper;
import com.example.mybookingservice.model.User;
import com.example.mybookingservice.repository.UserRepository;
import com.example.mybookingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegistrationResponseDto register(
            UserRegistrationRequestDto requestDto)
            throws RegistrationException {
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
    public UserRegistrationResponseDto updateRoleByUserId(
            Long id, UserUpdateRoleRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find entity by id: " + id));
        user.setRoleName(requestDto.roleName());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserRegistrationResponseDto getCurrentUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find user")
        );
        return userMapper.toUserResponse(user);
    }

    @Transactional
    @Override
    public UserRegistrationResponseDto updateInformationAboutUser(
            Long id, UserUpdateInformationRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("the user is not authenticated")
        );
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
