package com.example.mybookingservice.service.impl;

import com.example.mybookingservice.exception.EntityNotFoundException;
import com.example.mybookingservice.model.Address;
import com.example.mybookingservice.repository.AddressRepository;
import com.example.mybookingservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    
    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("can't find address by id:" + id));
    }
}
