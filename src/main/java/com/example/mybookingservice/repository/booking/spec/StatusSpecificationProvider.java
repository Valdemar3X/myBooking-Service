package com.example.mybookingservice.repository.booking.spec;

import com.example.mybookingservice.model.Booking;
import com.example.mybookingservice.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class StatusSpecificationProvider implements SpecificationProvider<Booking> {
    @Override
    public String getKey() {
        return "status";
    }

    @Override
    public Specification<Booking> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) ->
                root.get("status").in(Arrays.stream(params)
                        .toArray()));
    }
}
