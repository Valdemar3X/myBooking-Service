package com.example.mybookingservice.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class StripeConfig {
    @Value("${stripe.secret.key}")
    private String secretKey;
    @Value("${stripe.success.url}")
    private String successUrl;
    @Value("${stripe.cancel.url}")
    private String cancelUrl;
    @Value("${stripe.secret.endpoint}")
    private String endpointSecret;
}
