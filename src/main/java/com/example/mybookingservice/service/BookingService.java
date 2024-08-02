package com.example.mybookingservice.service;

import com.example.mybookingservice.dto.booking.BookingByParametersRequestDto;
import com.example.mybookingservice.dto.booking.BookingResponseDto;
import com.example.mybookingservice.dto.booking.CreateBookingRequestDto;
import com.example.mybookingservice.dto.booking.UpdateBookingRequestDto;
import com.example.mybookingservice.model.User;
import java.util.List;

public interface BookingService {
    BookingResponseDto createBooking(Long id, CreateBookingRequestDto requestDto);

    List<BookingResponseDto> getBookingsByParameters(
            BookingByParametersRequestDto parametersRequestDto);

    List<BookingResponseDto> getAllBookingsByUserId(Long id);

    BookingResponseDto getBookingById(Long id);

    void deleteBookingById(Long id);

    BookingResponseDto updateBookingById(Long id, UpdateBookingRequestDto requestDto, User user);
}
