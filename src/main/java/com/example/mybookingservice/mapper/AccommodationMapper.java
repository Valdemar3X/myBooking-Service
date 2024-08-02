package com.example.mybookingservice.mapper;

import com.example.mybookingservice.config.MapperConfig;
import com.example.mybookingservice.dto.accommodation.AccommodationResponseDto;
import com.example.mybookingservice.dto.accommodation.CreateAccommodationRequestDto;
import com.example.mybookingservice.model.Accommodation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface AccommodationMapper {
    @Mapping(source = "location", target = "addressResponseDto")
    AccommodationResponseDto toDto(Accommodation accommodation);

    @Mapping(source = "requestDto", target = "location")
    Accommodation toModel(CreateAccommodationRequestDto requestDto);
}
