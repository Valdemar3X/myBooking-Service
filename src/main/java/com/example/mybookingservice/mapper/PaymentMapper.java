package com.example.mybookingservice.mapper;

import com.example.mybookingservice.dto.payment.PaymentResponseDto;
import com.example.mybookingservice.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentResponseDto toDto(Payment payment) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setId(payment.getId());
        paymentResponseDto.setStatus(payment.getStatus());
        paymentResponseDto.setBookingId(payment.getBooking().getId());
        paymentResponseDto.setSessionUrl(payment.getSessionUrl());
        paymentResponseDto.setSessionId(payment.getSessionId());
        paymentResponseDto.setAmount(payment.getAmount());
        return paymentResponseDto;
    }
}
