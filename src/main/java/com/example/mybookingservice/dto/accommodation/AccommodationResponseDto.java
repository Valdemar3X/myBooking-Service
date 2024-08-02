package com.example.mybookingservice.dto.accommodation;

import com.example.mybookingservice.model.Accommodation;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationResponseDto {
    private Long id;
    private Accommodation.TypeAccommodation type;
    private AddressResponseDto addressResponseDto;
    private String size;
    private List<String> amenities;
    private BigDecimal dailyRate;
    private Integer availability;
}
