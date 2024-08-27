package com.example.mybookingservice.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentRequestDto {
    @Schema(example = "1")
    @NotEmpty(message = "Can`t be empty")
    @Positive
    private Long bookingId;
    @Schema(example = "usd")
    @NotEmpty(message = "Currency can't be empty")
    private String currency;

    @Schema(example = "Payment for car rental")
    @NotEmpty(message = "Description can't be empty")
    private String description;
}
