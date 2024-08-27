package com.example.mybookingservice.service;

import com.example.mybookingservice.dto.payment.CreatePaymentRequestDto;
import com.example.mybookingservice.dto.payment.PaymentResponseDto;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentService {

    @Transactional
    PaymentResponseDto createSession(CreatePaymentRequestDto requestDto);

    @Transactional
    ResponseEntity<Map<String, String>> getSuccess(String sessionId);

    ResponseEntity<Map<String, String>> getCancel();

    List<PaymentResponseDto> getPaymentsByUserId(Long userId);

    Boolean checkPaymentStatus(String sessionId);
}
