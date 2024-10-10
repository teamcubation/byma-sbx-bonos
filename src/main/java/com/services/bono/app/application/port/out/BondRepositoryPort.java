package com.services.bono.app.application.port.out;

import com.services.bono.app.domain.model.Bond;

import java.util.List;
import java.util.Optional;

public interface BondRepositoryPort {

    Bond findById(long id) throws Exception;
    List<Bond> getAll();
    Bond create(Bond bond) throws Exception;
    void deleteById(long id) throws Exception;

}
