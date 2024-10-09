package com.services.bond.app.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BondDTO {
    public static final String NAME_CANNOT_BE_NULL = "The name cannot be null";
    public static final String NAME_CANNOT_BE_EMPTY = "The name cannot be empty";
    public static final String PRICE_CANNOT_BE_NULL = "The price cannot be null";
    public static final String INTEREST_RATE_CANNOT_BE_NULL = "The interest rate cannot be null";
    public static final String CREATION_DATE_CANNOT_BE_NULL = "The creation date cannot be null";

    @NotNull(message = NAME_CANNOT_BE_NULL)
    @NotBlank(message = NAME_CANNOT_BE_EMPTY)
    private String name;

    @NotNull(message = PRICE_CANNOT_BE_NULL)
    private double price;

    @NotNull(message = INTEREST_RATE_CANNOT_BE_NULL)
    private double interestRate;

    @NotNull(message = CREATION_DATE_CANNOT_BE_NULL)
    private Date creationDate;
}