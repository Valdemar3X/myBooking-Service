package com.example.mybookingservice.mapper;

import com.example.mybookingservice.config.MapperConfig;
import com.example.mybookingservice.dto.accommodation.AccommodationResponseDto;
import com.example.mybookingservice.dto.accommodation.CreateAccommodationRequestDto;
import com.example.mybookingservice.model.Accommodation;
import com.example.mybookingservice.service.AddressService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = AddressMapper.class)
public interface AccommodationMapper {
    @Mapping(source = "location.id", target = "locationId")
AccommodationResponseDto toDto(Accommodation accommodation);

    @Mapping(target = "location", source = "locationId", qualifiedByName = "getLocationById")
Accommodation toModel(
            CreateAccommodationRequestDto requestDto, @Context AddressService addressService);
}
