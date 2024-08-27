package com.example.mybookingservice.controller;

import com.example.mybookingservice.config.StripeConfig;
import com.example.mybookingservice.dto.payment.CreatePaymentRequestDto;
import com.example.mybookingservice.dto.payment.PaymentResponseDto;
import com.example.mybookingservice.security.JwtUtil;
import com.example.mybookingservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final StripeConfig stripeConfig;
    private final JwtUtil jwtUtil;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping()
    @Operation(summary = "Create a new payment session",
            description = "Create a new payment session to pay for the booking")
    public PaymentResponseDto createPaymentSession(
            @RequestBody @Valid CreatePaymentRequestDto requestDto) {
        return paymentService.createSession(requestDto);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PermitAll
    @Operation(summary = "get response", description = "get response success")
    @GetMapping("/success")
    public ResponseEntity<Map<String, String>> getSuccess(
            @RequestParam("session_id") String sessionId
    ) {
        return paymentService.getSuccess(sessionId);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "get response", description = "get response cancel")
    @GetMapping("/cancel")
    public ResponseEntity<Map<String, String>> getCancel() {
        return paymentService.getCancel();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get payments by user id")
    public List<PaymentResponseDto> getPaymentsUserById(
            @Parameter(description = "Payment's id to find it")
            @PathVariable Long userId) {
        return paymentService.getPaymentsByUserId(userId);
    }
}

