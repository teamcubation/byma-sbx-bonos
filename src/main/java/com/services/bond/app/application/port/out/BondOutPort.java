package com.services.bond.app.application.port.out;

import com.services.bond.app.domain.model.Bond;

import java.util.List;

public interface BondOutPort {
    Bond findById(long id) throws Exception;
    List<Bond> getAll();
    Bond create(Bond bond) throws Exception;
    void deleteById(long id) throws Exception;
}
