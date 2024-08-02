package com.example.mybookingservice.repository.accommodation;

import com.example.mybookingservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
