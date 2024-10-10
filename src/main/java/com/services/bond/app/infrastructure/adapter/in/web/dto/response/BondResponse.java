package com.services.bond.app.infrastructure.adapter.in.web.dto.response;


import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BondResponse {

    private String name;
    private Double price;
    private Double interestRate;
    private LocalDate creationDate;

}
