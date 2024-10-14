package com.services.bond.app.application.port.in;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import com.services.bond.app.domain.model.Bond;

public interface BondInPort {

    Bond create(Bond bond) throws Exception;

    Bond findByID(long id) throws Exception;

    Bond update(long id, Bond bond) throws Exception;

    void delete(long id) throws BondNotFoundException;

    Iterable<Bond> getAll();
}
