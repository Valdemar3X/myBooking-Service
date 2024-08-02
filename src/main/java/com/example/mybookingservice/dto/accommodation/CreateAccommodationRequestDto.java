package com.example.mybookingservice.dto.accommodation;

import com.example.mybookingservice.model.Accommodation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccommodationRequestDto {
    @NotNull
    @Enumerated(EnumType.STRING)
    private Accommodation.TypeAccommodation type;

    @NotBlank
    private CreateAddressRequestDto requestDto;

    @NotBlank
    private String size;

    @NotNull
    private List<String> amenities;

    @NotNull
    @Min(0)
    private BigDecimal dailyRate;

    @NotNull
    private Integer availability;
}

