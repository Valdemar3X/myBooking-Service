package com.example.mybookingservice.service.impl;

import com.example.mybookingservice.config.StripeConfig;
import com.example.mybookingservice.model.Payment;
import com.example.mybookingservice.service.StripePaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripePaymentServiceImpl implements StripePaymentService {
    private final StripeConfig stripeConfig;

    @Override
    public Session createSession(Payment payment) {
        Stripe.apiKey = stripeConfig.getSecretKey();
        SessionCreateParams.LineItem.PriceData.ProductData productData
                = SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName("booking")
                .build();
        SessionCreateParams.LineItem.PriceData priceData
                = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount(payment.getAmount().longValue() * 100)
                .setProductData(productData)
                .build();
        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setPriceData(priceData)
                .setQuantity(1L)
                .build();
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/api/payments/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl("http://localhost:8080/api/payments/cancel")
                .addLineItem(lineItem)
                .build();
        try {
            return Session.create(params);
        } catch (StripeException e) {
            throw new RuntimeException("Can't create Stripe payment Session ", e);
        }
    }

    @Override
    public Boolean checkPaymentStatus(String sessionId) {
        Stripe.apiKey = stripeConfig.getSecretKey();
        try {
            Session session = Session.retrieve(sessionId);
            String status = session.getStatus();
            if ("complete".equals(status)) {
                return true;
            } else {
                return false;
            }

        } catch (StripeException e) {
            throw new RuntimeException("Can't retrieve Stripe session", e);
        }
    }
}
