package com.example.mybookingservice.controller;

import com.example.mybookingservice.dto.accommodation.AccommodationResponseDto;
import com.example.mybookingservice.dto.accommodation.CreateAccommodationRequestDto;
import com.example.mybookingservice.service.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Accommodation management",
        description = "Endpoints for management accommodations")
@RestController
@RequestMapping(value = "/accommodations")
@RequiredArgsConstructor
public class AccommodationController {
    private final AccommodationService accommodationService;

    @Operation(summary = "Add accommodation", description = "Add accommodation to DB")
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public AccommodationResponseDto createAccommodation(
            @RequestBody @Valid CreateAccommodationRequestDto requestDto) {
        return accommodationService.save(requestDto);
    }

    @Operation(summary = "Get accommodations", description = "Get all accommodations")
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public List<AccommodationResponseDto> getAll(Pageable pageable) {

        return accommodationService.getAll(pageable);
    }

    @Operation(summary = "Get accommodation", description = "Get accommodation by id")
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public AccommodationResponseDto getAccommodationById(@PathVariable Long id) {

        return accommodationService.getById(id);
    }

    @Operation(summary = "Delete accommodation", description = "Delete accommodation by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        accommodationService.deleteById(id);
    }

    @Operation(summary = "Update accommodation", description = "Update accommodation by id")
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public AccommodationResponseDto updateByID(@PathVariable Long id,
                                  @RequestBody CreateAccommodationRequestDto requestDto) {
        return accommodationService.updateAccommodationById(id, requestDto);
    }
}

