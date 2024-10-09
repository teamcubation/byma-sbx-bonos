package com.services.bond.app.infrastructure.adapter.in.web.mapper;

import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.infrastructure.adapter.in.web.dto.BondDTO;

public class BondMapper {

    public static BondDTO bondToBondDTO(Bond bond) {
        return BondDTO.builder()
                .name(bond.getName())
                .price(bond.getPrice())
                .interestRate(bond.getInterestRate())
                .creationDate(bond.getCreationDate())
                .build();
    }

    public static Bond bondDTOToBond(BondDTO bondDTO) {
        return Bond.builder()
                .name(bondDTO.getName())
                .price(bondDTO.getPrice())
                .interestRate(bondDTO.getInterestRate())
                .creationDate(bondDTO.getCreationDate())
                .build();
    }
}