package com.example.mybookingservice.controller;

import com.example.mybookingservice.dto.booking.BookingByParametersRequestDto;
import com.example.mybookingservice.dto.booking.BookingResponseDto;
import com.example.mybookingservice.dto.booking.CreateBookingRequestDto;
import com.example.mybookingservice.dto.booking.UpdateBookingRequestDto;
import com.example.mybookingservice.model.User;
import com.example.mybookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Booking management", description = "Endpoints for management bookings")
@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @Operation(summary = "Create booking",
            description = "Endpoint for create booking")
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public BookingResponseDto createBooking(
            Authentication authentication,
            @RequestBody @Valid CreateBookingRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return bookingService.createBooking(user.getId(), requestDto);
    }

    @Operation(summary = "Get bookings",
            description = "Endpoint for get bookings by parameters")
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public List<BookingResponseDto> getBookingsByParams(
            BookingByParametersRequestDto parametersDto) {
        return bookingService.getBookingsByParameters(parametersDto);
    }

    @Operation(summary = "Get all bookings",
            description = "Endpoint for get all bookings for user which is logged")
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/my")
    public List<BookingResponseDto> getAllBookingsByCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return bookingService.getAllBookingsByUserId(user.getId());
    }

    @Operation(summary = "Get booking",
            description = "Endpoint for get booking by id")
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public BookingResponseDto getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @Operation(summary = "Delete booking", description = "Delete booking by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
    }

    @Operation(summary = "Update booking",
            description = "Allows users to update their booking details by id")
    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{id}")
    public BookingResponseDto updateByID(@PathVariable Long id,
                                         @RequestBody @Valid UpdateBookingRequestDto requestDto,
                                         Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return bookingService.updateBookingById(id, requestDto, user);
    }
}
