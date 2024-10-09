package com.services.bono.app.application.port.out;

import com.services.bono.app.domain.model.Bono;

import java.util.List;
import java.util.Optional;

public interface BonoRepositoryPort {

    Optional<Bono> findById(Long id) throws Exception;
    List<Bono> getALl();
    Bono create(Bono bono) throws Exception;
    void deleteById(Long id) throws Exception;

}
