package com.services.bono.app.infrastructure.adapter.out.persistence.mapper;


import com.services.bono.app.domain.model.BonoModel;
import com.services.bono.app.infrastructure.adapter.out.persistence.entity.BonoEntity;

import java.time.LocalDate;

public class BonoMapper {

    public static BonoModel mapToModel(BonoEntity bonoEntity)  {
        return BonoModel.builder()
                .id(bonoEntity.getId())
                .name(bonoEntity.getName())  // Cambiado a 'nombre'
                .price(bonoEntity.getPrice())  // Cambiado a 'precio'
                .interestRate(bonoEntity.getInterestRate())
                .creationDate(bonoEntity.getCreationDate())  // Asigna la fecha desde la entidad
                .build();
    }

    public static BonoEntity mapToEntity(BonoModel bonoModel) {
        return BonoEntity.builder()
                .id(bonoModel.getId())
                .name(bonoModel.getName())  // Cambiado a 'name'
                .price(bonoModel.getPrice())  // Cambiado a 'price'
                .interestRate(bonoModel.getInterestRate())
                .creationDate(LocalDate.now())  // Asigna la fecha desde el modelo
                .build();
    }


}
