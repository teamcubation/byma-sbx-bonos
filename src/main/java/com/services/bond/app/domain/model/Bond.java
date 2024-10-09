package com.services.bond.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bond {
    private String id;
    private String name;
    private double price;
    private double interestRate;
    private Date creationDate;
}
