package com.example.mybookingservice.mapper;

import com.example.mybookingservice.config.MapperConfig;
import com.example.mybookingservice.dto.accommodation.AddressResponseDto;
import com.example.mybookingservice.dto.accommodation.CreateAddressRequestDto;
import com.example.mybookingservice.model.Address;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface AddressMapper {
    AddressResponseDto toDto(Address address);

    Address toModel(CreateAddressRequestDto requestDto);
}
