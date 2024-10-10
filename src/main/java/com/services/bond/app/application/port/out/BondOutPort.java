package com.services.bond.app.application.port.out;

import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.domain.model.exception.BondNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BondOutPort {
    Optional<Bond> findById(long id);
    List<Bond> getAll();
    Bond create(Bond bond) throws BondNotFoundException;
    void deleteById(long id) throws Exception;
}
