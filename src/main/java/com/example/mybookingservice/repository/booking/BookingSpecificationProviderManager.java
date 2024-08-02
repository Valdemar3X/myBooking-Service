package com.example.mybookingservice.repository.booking;

import com.example.mybookingservice.model.Booking;
import com.example.mybookingservice.repository.SpecificationProvider;
import com.example.mybookingservice.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookingSpecificationProviderManager implements SpecificationProviderManager<Booking> {
    private final List<SpecificationProvider<Booking>> bookSpecificationProvider;

    @Override
    public SpecificationProvider<Booking> getSpecificationProvider(String key) {
        return bookSpecificationProvider.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Can't find correct specification provider for key: "
                                + key));
    }
}
