package com.example.mybookingservice.dto.accommodation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {
    private Long id;
    private String cityName;

    private String str;

    private String numberOfBuilding;
    private Integer numberOfApartment;
}
