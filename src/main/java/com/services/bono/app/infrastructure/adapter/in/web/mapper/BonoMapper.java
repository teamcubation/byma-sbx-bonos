package com.services.bono.app.infrastructure.adapter.in.web.mapper;

import com.services.bono.app.infrastructure.adapter.in.web.dto.BonoDTO;

public class BonoMapper {

    public static BonoDTO bonoToBonoDTO(Bono bono) {
        return BonoDTO.builder()
                .name(bono.getName())
                .price(bono.getPrice())
                .tasaDeInteres(bono.getTasaDeInteres())
                .build();
    }

    public static Bono bonoDTOToBono(BonoDTO bonoDTO) {
        return Bono.builder()
                .name(bonoDTO.getName())
                .price(bonoDTO.getPrice())
                .tasaDeInteres(bonoDTO.getTasaDeInteres())
                .build();
    }
}