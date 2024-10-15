package com.services.bond.app.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BondRequest {
    public static final String NAME_CANNOT_BE_EMPTY = "The name cannot be empty";
    public static final String PRICE_CANNOT_BE_NULL = "The price cannot be null";
    public static final String INTEREST_RATE_CANNOT_BE_NULL = "The interest rate cannot be null";
    public static final String THE_PRICE_CANNOT_BE_NEGATIVE = "The price cannot be negative";
    private static final String THE_INTEREST_RATE_CANNOT_BE_NEGATIVE = "The interest rate cannot be negative";

    @NotBlank(message = NAME_CANNOT_BE_EMPTY)
    private String name;

    @Min(value = 0, message = THE_PRICE_CANNOT_BE_NEGATIVE)
    @NotNull(message = PRICE_CANNOT_BE_NULL)
    private Double price;

    @Min(value = 0, message = THE_INTEREST_RATE_CANNOT_BE_NEGATIVE)
    @NotNull(message = INTEREST_RATE_CANNOT_BE_NULL)
    private Double interestRate;
}