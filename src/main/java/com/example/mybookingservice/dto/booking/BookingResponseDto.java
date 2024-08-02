package com.example.mybookingservice.dto.booking;

import com.example.mybookingservice.model.Booking;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {
    private Long id;
    private Long userId;
    private Long accommodationId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @Enumerated(EnumType.STRING)
    private Booking.StatusBooking status;
}
