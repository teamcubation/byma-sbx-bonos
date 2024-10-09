package com.services.bono.app.application.port.in;

import com.services.bono.app.domain.model.Bono;

public interface BonoServicePort {

    void registrarBono(Bono bono);

    Bono buscarBonoPorID(Long id);

    Bono actualizarBono(Long id, Bono bono);

    void eliminarBono(Long id);

    Iterable<Bono> consultarTodosLosBonos();
}
