package com.services.bono.app.infrastructure.adapter.out.persistence.mapper;

import com.services.bono.app.domain.model.Bono;
import com.services.bono.app.infrastructure.adapter.out.persistence.entity.BonoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BonoPersistenceMapper {
    public static Bono bonoEntityToBonoModel(BonoEntity bonoEntity) {
        return Bono.builder()
                .id(bonoEntity.getId())
                .name(bonoEntity.getName())
                .price(bonoEntity.getPrice())
                .interestRate(bonoEntity.getInterestRate())
                .build();
    }

    public static BonoEntity bonoModelToBonoEntity(Bono bono) {
        return BonoEntity.builder()
                .id(bono.getId())
                .name(bono.getName())
                .price(bono.getPrice())
                .interestRate(bono.getInterestRate())
                .build();
    }

    public static List<Bono> bonoEntitiesToBonoModels(List<BonoEntity> bonoEntities){
        return bonoEntities.stream()
                .map(BonoPersistenceMapper::bonoEntityToBonoModel)
                .collect(Collectors.toList());
    }
}
