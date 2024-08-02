package com.example.mybookingservice.repository.accommodation;

import com.example.mybookingservice.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
