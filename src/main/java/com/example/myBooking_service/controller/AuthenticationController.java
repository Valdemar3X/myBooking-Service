package com.example.myBooking_service.controller;

import com.example.myBooking_service.dto.user.UserRegistrationRequestDto;
import com.example.myBooking_service.dto.user.UserRegistrationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Authentication management", description = "Endpoints for authentication users")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    @Operation(summary = "Registration a user", description = "Registration of a new User" )
    @PostMapping("/register")
    public UserRegistrationResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }
    @Operation(summary = "Login a user", description = "Receiving a token after user login")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
