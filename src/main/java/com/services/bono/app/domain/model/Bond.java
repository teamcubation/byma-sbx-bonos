package com.services.bono.app.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bond {
    private Long id;
    private String name;
    private double price;
    private double interestRate;
}
