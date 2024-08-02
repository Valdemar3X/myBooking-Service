package com.example.mybookingservice.repository.booking;

import com.example.mybookingservice.dto.booking.BookingByParametersRequestDto;
import com.example.mybookingservice.model.Booking;
import com.example.mybookingservice.repository.SpecificationBuilder;
import com.example.mybookingservice.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingSpecificationBuilder
        implements SpecificationBuilder<Booking, BookingByParametersRequestDto> {
    private final SpecificationProviderManager<Booking> specificationProviderManager;

    @Override
    public Specification<Booking> build(BookingByParametersRequestDto searchParameters) {
        Specification<Booking> specification = Specification.where(null);
        if (searchParameters.userId() != null) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider("userId")
                    .getSpecification(new String[]{searchParameters.userId().toString()}));
        }
        if (searchParameters.status() != null) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider("status")
                    .getSpecification(new String[]{searchParameters.status().toString()}));
        }
        return specification;
    }
}
