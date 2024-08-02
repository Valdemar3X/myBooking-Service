package com.example.mybookingservice.dto.booking;

import com.example.mybookingservice.model.Booking;

public record BookingByParametersRequestDto(
        Long userId,
        Booking.StatusBooking status) {
}
