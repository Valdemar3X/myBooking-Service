package com.example.mybookingservice.dto.booking;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingRequestDto {
    @NotBlank
    private Long accommodationId;
    @NotBlank
    private LocalDate checkIn;
    @NotBlank
    private LocalDate checkOut;
}
