package com.example.mybookingservice.dto.payment;

import com.example.mybookingservice.model.Payment;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {
    private Long id;
    private Payment.PaymentStatus status;
    private Long bookingId;
    private String sessionUrl;
    private String sessionId;
    private BigDecimal amount;


}
