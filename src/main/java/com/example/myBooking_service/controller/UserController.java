package com.example.myBooking_service.controller;

import com.example.myBooking_service.dto.user.UserRegistrationRequestDto;
import com.example.myBooking_service.dto.user.UserRegistrationResponseDto;
import com.example.myBooking_service.dto.user.UserUpdateInformationRequestDto;
import com.example.myBooking_service.dto.user.UserUpdateRoleRequestDto;
import com.example.myBooking_service.model.User;
import com.example.myBooking_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User management", description = "Endpoints for management of role changes and user information")
@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/users")
public class UserController {
    private final UserService userService;
    @Operation(summary =  "Update role", description = "Endpoint for update users role")
    // @PreAuthorize("hasRole('CUSTOMER') or hasRole('MANAGER')")
    @PutMapping("/{id}/role")
    public UserRegistrationResponseDto updateRoleByUserId
            (@PathVariable Long id,
             @RequestBody UserUpdateRoleRequestDto requestDto) {
       return userService.updateRoleByUserId(id, requestDto);
    }

    @Operation(summary =  "Get current user", description = "Endpoint for get current user that is logged in")
    @GetMapping("/users/me")
   public UserRegistrationResponseDto getCurrentUser(Authentication authentication) {
       User user = (User) authentication.getPrincipal();
       return userService.getCurrentUser(user.getId());
   }
   @Operation(summary =  "Update information", description = "Endpoint for update information about" +
           "current user that is logged in")
   @PutMapping("/users/me")
    public UserRegistrationResponseDto updateInformation(
            Authentication authentication,
            @RequestBody UserUpdateInformationRequestDto requestDto) {
       User user = (User) authentication.getPrincipal();
       return userService.updateInformationAboutUser(user.getId(), requestDto);
   }
}
