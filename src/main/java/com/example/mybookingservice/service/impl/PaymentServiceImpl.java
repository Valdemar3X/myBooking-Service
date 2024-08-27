package com.example.mybookingservice.service.impl;

import com.example.mybookingservice.dto.payment.CreatePaymentRequestDto;
import com.example.mybookingservice.dto.payment.PaymentResponseDto;
import com.example.mybookingservice.exception.EntityNotFoundException;
import com.example.mybookingservice.mapper.PaymentMapper;
import com.example.mybookingservice.model.Accommodation;
import com.example.mybookingservice.model.Booking;
import com.example.mybookingservice.model.Payment;
import com.example.mybookingservice.repository.accommodation.AccommodationRepository;
import com.example.mybookingservice.repository.booking.BookingRepository;
import com.example.mybookingservice.repository.payment.PaymentRepository;
import com.example.mybookingservice.service.PaymentService;
import com.example.mybookingservice.service.StripePaymentService;
import com.stripe.model.checkout.Session;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final StripePaymentService stripePaymentService;
    private final BookingRepository bookingRepository;
    private final AccommodationRepository accommodationRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    @Override
    public PaymentResponseDto createSession(CreatePaymentRequestDto requestDto) {
        Booking booking = bookingRepository.findById(requestDto.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find booking by id " + requestDto.getBookingId()));
        Long accommodationId = booking.getAccommodation().getId();
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(
                () -> new EntityNotFoundException("Can't find booking by id " + accommodationId));
        Payment payment = new Payment();
        payment.setBooking(booking);
        BigDecimal bookingAmount = calculateBookingAmount(booking, accommodation);
        payment.setAmount(bookingAmount);
        Session session = stripePaymentService.createSession(payment);
        String sessionId = session.getId();
        payment.setSessionUrl(session.getUrl());
        payment.setSessionId(sessionId);
        payment.setStatus(Payment.PaymentStatus.PENDING);
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDto(savedPayment);
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, String>> getSuccess(String sessionId) {
        if (checkPaymentStatus(sessionId)) {
            Payment payment = paymentRepository.findBySessionId(sessionId);
            payment.setStatus(Payment.PaymentStatus.PAID);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Payment successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return getCancel();
    }

    @Override
    public ResponseEntity<Map<String, String>> getCancel() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment canceled");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public Boolean checkPaymentStatus(String sessionId) {
        return stripePaymentService.checkPaymentStatus(sessionId);
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByUserId(Long userId) {
        return paymentRepository.findAllByUserId(userId).stream()
                .map(p -> paymentMapper.toDto(p)).collect(Collectors.toList());
    }

    private BigDecimal calculateBookingAmount(Booking booking, Accommodation accommodation) {
        long numberOfDays = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
        return accommodation.getDailyRate().multiply(new BigDecimal(numberOfDays));
    }
}
