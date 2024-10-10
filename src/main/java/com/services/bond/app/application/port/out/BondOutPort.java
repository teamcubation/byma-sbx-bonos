package com.services.bond.app.application.port.out;

import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.domain.model.exception.BondDuplicateException;
import com.services.bond.app.domain.model.exception.BondNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BondOutPort {
    Optional<Bond> findById(long id);
    List<Bond> getAll();
    Bond save(Bond bond) throws BondDuplicateException;
    void deleteById(long id) throws Exception;
    boolean existsByNameIgnoreCase(String name);

}
