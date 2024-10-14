package com.services.bond.app.infrastructure.adapter.out.persistence.mapper;


import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.infrastructure.adapter.out.persistence.entity.BondEntity;
import com.services.bond.app.application.service.exception.BondNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BondPersistenceMapper {

    public static Bond bondEntityToBondModel(BondEntity bondEntity) {
        if (bondEntity == null){
            throw new BondNotFoundException();
        }
        return Bond.builder()
                .id(bondEntity.getId())
                .name(bondEntity.getName())
                .price(bondEntity.getPrice())
                .interestRate(bondEntity.getInterestRate())
                .creationDate(bondEntity.getCreationDate())
                .build();
    }

    public static BondEntity bondModelToBondEntity(Bond bond) {
        if (bond == null) {
            throw new BondNotFoundException();
        }
        return BondEntity.builder()
                .id(bond.getId())
                .name(bond.getName())
                .price(bond.getPrice())
                .interestRate(bond.getInterestRate())
                .creationDate(LocalDate.now())
                .build();
    }

    public static List<Bond> bondEntitiesToBondModels(List<BondEntity> bondEntities){
        if (bondEntities == null) {
            throw new BondNotFoundException();
        }
        return bondEntities.stream()
                .map(BondPersistenceMapper::bondEntityToBondModel)
                .collect(Collectors.toList());
    }

}
