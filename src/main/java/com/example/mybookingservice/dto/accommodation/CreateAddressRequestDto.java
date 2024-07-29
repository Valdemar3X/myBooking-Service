package com.example.mybookingservice.dto.accommodation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequestDto {
    @NotBlank
    private String cityName;
    @NotBlank
    private String str;
    @NotNull
    private String numberOfBuilding;
    @NotNull
    private Integer numberOfApartment;
}
