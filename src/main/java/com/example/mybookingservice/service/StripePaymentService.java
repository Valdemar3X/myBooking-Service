package com.example.mybookingservice.service;

import com.example.mybookingservice.model.Payment;
import com.stripe.model.checkout.Session;

public interface StripePaymentService {

    Session createSession(Payment payment);

    Boolean checkPaymentStatus(String sessionId);
}
