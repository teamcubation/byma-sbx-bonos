package com.services.bono.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonoModel {
    private long id;
    private String name;
    private double price;
    private double interestRate;
    private LocalDate creationDate;
}