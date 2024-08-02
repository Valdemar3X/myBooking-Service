package com.example.mybookingservice.mapper;

import com.example.mybookingservice.config.MapperConfig;
import com.example.mybookingservice.dto.booking.BookingResponseDto;
import com.example.mybookingservice.dto.booking.CreateBookingRequestDto;
import com.example.mybookingservice.dto.booking.UpdateBookingRequestDto;
import com.example.mybookingservice.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface BookingMapper {
    @Mapping(source = "accommodation.id", target = "accommodationId")
    @Mapping(source = "user.id", target = "userId")
    BookingResponseDto toDto(Booking booking);

    @Mapping(source = "accommodationId", target = "accommodation.id")
    Booking toModel(CreateBookingRequestDto requestDto);

    Booking toUpdatedModel(UpdateBookingRequestDto requestDto);
}
