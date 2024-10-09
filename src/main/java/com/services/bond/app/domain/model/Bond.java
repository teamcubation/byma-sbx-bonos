package com.services.bond.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bond {
    private long id;
    private String name;
    private double price;
    private double interestRate;
    private LocalDate creationDate;
}

