package com.example.mybookingservice.service.impl;

import com.example.mybookingservice.dto.accommodation.AccommodationResponseDto;
import com.example.mybookingservice.dto.accommodation.CreateAccommodationRequestDto;
import com.example.mybookingservice.exception.EntityNotFoundException;
import com.example.mybookingservice.mapper.AccommodationMapper;
import com.example.mybookingservice.mapper.AddressMapper;
import com.example.mybookingservice.model.Accommodation;
import com.example.mybookingservice.repository.accommodation.AccommodationRepository;
import com.example.mybookingservice.repository.accommodation.AddressRepository;
import com.example.mybookingservice.service.AccommodationService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AccommodationResponseDto save(CreateAccommodationRequestDto requestDto) {
        Accommodation accommodation = accommodationMapper.toModel(requestDto);
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        return accommodationMapper.toDto(savedAccommodation);
    }

    @Override
    public List<AccommodationResponseDto> getAll(Pageable pageable) {
        return accommodationRepository
             .findAll()
             .stream()
             .map(a -> accommodationMapper.toDto(a))
             .collect(Collectors.toList());
    }

    @Override
    public AccommodationResponseDto getById(Long id) {
        return accommodationMapper.toDto(accommodationRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException("can't find accommodation by id" + id)));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Transactional
    @Override
    public AccommodationResponseDto updateAccommodationById(
            Long id, CreateAccommodationRequestDto requestDto) {
        Accommodation accommodation = accommodationMapper.toModel(requestDto);
        Accommodation updateAccommodation = null;
        if (accommodationRepository.existsById(id)) {
            accommodation.setId(id);
            accommodation.getLocation().setId(id);
            updateAccommodation = accommodationRepository.save(accommodation);
        } else {
            throw new EntityNotFoundException("can't find accommodation by id" + id);
        }
        return accommodationMapper.toDto(updateAccommodation);
    }
}
