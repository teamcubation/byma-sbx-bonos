package com.services.bond.app.application.port.in;

import com.services.bond.app.application.service.exception.BondDuplicateException;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import com.services.bond.app.domain.model.Bond;

import java.util.List;

public interface BondInPort {

    Bond create(Bond bond) throws BondDuplicateException;

    Bond findById(long id) throws BondNotFoundException;

    Bond update(long id, Bond bond) throws BondNotFoundException, BondDuplicateException;

    void delete(long id) throws BondNotFoundException;

    List<Bond> getAll();
}
