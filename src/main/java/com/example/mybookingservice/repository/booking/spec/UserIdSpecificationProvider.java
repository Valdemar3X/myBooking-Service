package com.example.mybookingservice.repository.booking.spec;

import com.example.mybookingservice.model.Booking;
import com.example.mybookingservice.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserIdSpecificationProvider implements SpecificationProvider<Booking> {
    @Override
    public String getKey() {
        return "userId";
    }

    @Override
    public Specification<Booking> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("id"), Long.valueOf(params[0]));
    }
}
