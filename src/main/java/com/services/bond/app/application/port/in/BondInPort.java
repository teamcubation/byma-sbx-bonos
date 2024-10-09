package com.services.bond.app.application.port.in;
import com.services.bond.app.domain.model.Bond;

public interface BondInPort {

    Bond register(Bond bond);

    Bond getByID(long id);

    Bond update(long id, Bond bond);

    Bond delete(long id);

    Iterable<Bond> getAll();
}
