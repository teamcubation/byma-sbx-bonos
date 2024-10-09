package com.services.bono.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bono {
    private String id;
    private String name;
    private Double price;
    private Double tasaDeInteres;
    private Date fechaCreacion;
}
