package com.services.bond.app.application.port.in;
import com.services.bond.app.domain.model.Bond;

import java.util.List;

public interface BondInPort {

    Bond create(Bond bond) throws Exception;

    Bond findByID(long id) throws Exception;

    Bond update(long id, Bond bond) throws Exception;

    void delete(long id) throws Exception;

    List<Bond> getAll();
}
