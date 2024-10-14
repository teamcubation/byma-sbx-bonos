package com.services.bond.app.infrastructure.adapter.in.web.mapper;

import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.infrastructure.adapter.in.web.dto.request.BondRequest;
import com.services.bond.app.infrastructure.adapter.in.web.dto.response.BondResponse;

public class BondMapper {

    public static Bond bondRequestToBond(BondRequest bondRequest) {
        return Bond.builder()
                .name(bondRequest.getName())
                .price(bondRequest.getPrice())
                .interestRate(bondRequest.getInterestRate())
                .build();
    }

    public static BondResponse bondToBondResponse(Bond bond) {
        return BondResponse.builder()
                .name(bond.getName())
                .price(bond.getPrice())
                .interestRate(bond.getInterestRate())
                .creationDate(bond.getCreationDate())
                .build();
    }



}