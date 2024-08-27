package com.example.mybookingservice.repository.payment;

import com.example.mybookingservice.model.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p "
            + "LEFT JOIN FETCH p.booking pb "
            + "LEFT JOIN FETCH pb.accommodation pba "
            + "LEFT JOIN FETCH pb.user pbu "
            + "WHERE pbu.id = :userId AND p.isDeleted = FALSE")
    List<Payment> findAllByUserId(Long userId);

    @Query("SELECT p FROM Payment p WHERE p.sessionId = :sessionId AND p.isDeleted = FALSE")
    Payment findBySessionId(String sessionId);
}
