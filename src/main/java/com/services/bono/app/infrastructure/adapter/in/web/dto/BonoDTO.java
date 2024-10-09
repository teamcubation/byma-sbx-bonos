package com.services.bono.app.infrastructure.adapter.in.web.dto;

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
public class BonoDTO {
    public static final String EL_NOMBRE_NO_PUEDE_SER_NULO = "El nombre no puede ser nulo";
    public static final String EL_NOMBRE_NO_PUEDE_SER_VACIO = "El nombre no puede ser vacio";
    public static final String EL_PRECIO_NO_PUEDE_SER_NULO = "El precio no puede ser nulo";
    public static final String LA_TASA_DE_INTERES_NO_PUEDE_SER_NULA = "La tasa de interes no puede ser nula";
    public static final String LA_FECHA_DE_CREACION_NO_PUEDE_SER_NULA = "La fecha de creacion no puede ser nula";
    @NotNull(message = EL_NOMBRE_NO_PUEDE_SER_NULO)
    @NotBlank(message = EL_NOMBRE_NO_PUEDE_SER_VACIO)
    private String name;
    @NotNull(message = EL_PRECIO_NO_PUEDE_SER_NULO)
    private Double price;
    @NotNull(message = LA_TASA_DE_INTERES_NO_PUEDE_SER_NULA)
    private Double tasaDeInteres;
    @NotNull(message = LA_FECHA_DE_CREACION_NO_PUEDE_SER_NULA)
    private Date fechaCreacion;
}