package com.example.myBooking_service.controller;

import com.example.myBooking_service.dto.user.RegistrationUserRequestDto;
import com.example.myBooking_service.dto.user.RegistrationUserResponseDto;
import com.example.myBooking_service.dto.user.UserLoginRequestDto;
import com.example.myBooking_service.dto.user.UserLoginResponseDto;
import com.example.myBooking_service.security.AuthenticationService;
import com.example.myBooking_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public RegistrationUserResponseDto register(@RequestBody @Valid RegistrationUserRequestDto requestDto) {
        return userService.register(requestDto);
    }
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
