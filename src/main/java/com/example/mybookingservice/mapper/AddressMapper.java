package com.example.mybookingservice.mapper;

import com.example.mybookingservice.config.MapperConfig;
import com.example.mybookingservice.model.Address;
import com.example.mybookingservice.service.AddressService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface AddressMapper {

    @Named("getLocationById")
    default Address getLocationById(Long id, @Context AddressService addressService) {
        return addressService.findById(id);
    }

}
