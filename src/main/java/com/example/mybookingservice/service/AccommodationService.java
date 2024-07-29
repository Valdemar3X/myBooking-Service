package com.example.mybookingservice.service;

import com.example.mybookingservice.dto.accommodation.AccommodationResponseDto;
import com.example.mybookingservice.dto.accommodation.CreateAccommodationRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AccommodationService {
    AccommodationResponseDto save(CreateAccommodationRequestDto requestDto);

    List<AccommodationResponseDto> getAll(Pageable pageable);

    AccommodationResponseDto getById(Long id);

    void deleteById(Long id);

    AccommodationResponseDto updateAccommodationById(
            Long id, CreateAccommodationRequestDto requestDto);

}
