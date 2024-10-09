package com.services.bond.app.infrastructure.adapter.out.persistence.mapper;


import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.infrastructure.adapter.out.persistence.entity.BondEntity;

import java.time.LocalDate;

public class BondMapper {

    public static Bond mapToModel(BondEntity bondEntity)  {
        return Bond.builder()
                .id(bondEntity.getId())
                .name(bondEntity.getName())
                .price(bondEntity.getPrice())
                .interestRate(bondEntity.getInterestRate())
                .creationDate(bondEntity.getCreationDate())
                .build();
    }

    public static BondEntity mapToEntity(Bond bondModel) {
        return BondEntity.builder()
                .id(bondModel.getId())
                .name(bondModel.getName())
                .price(bondModel.getPrice())
                .interestRate(bondModel.getInterestRate())
                .creationDate(LocalDate.now())  
                .build();
    }


}
