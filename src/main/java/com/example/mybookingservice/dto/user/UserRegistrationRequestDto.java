package com.example.mybookingservice.dto.user;

import com.example.mybookingservice.validation.FieldMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldMatch(field = "password",
        fieldMatch = "repeatPassword",
        message = "Password fields must match")
public class UserRegistrationRequestDto {
    @NotBlank
    @Size(min = 8, max = 50)
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
    @NotBlank
    @Size(min = 8, max = 100)
    private String repeatPassword;
}
