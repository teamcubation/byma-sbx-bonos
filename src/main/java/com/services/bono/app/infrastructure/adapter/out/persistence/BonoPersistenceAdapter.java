package com.services.bono.app.infrastructure.adapter.out.persistence;

import com.services.bono.app.application.port.out.BonoRepositoryPort;
import com.services.bono.app.domain.model.Bono;
import com.services.bono.app.domain.model.exception.BonoNotFoundException;
import com.services.bono.app.infrastructure.adapter.out.persistence.mapper.BonoPersistenceMapper;
import com.services.bono.app.infrastructure.adapter.out.persistence.repository.BonoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BonoPersistenceAdapter implements BonoRepositoryPort {

    private final BonoRepository bonoRepository;

    @Override
    public Optional<Bono> findById(Long id) throws Exception {
        return Optional.ofNullable(bonoRepository.findById(id)
                .map(BonoPersistenceMapper::bonoEntityToBonoModel)
                .orElseThrow(BonoNotFoundException::new));
    }

    @Override
    public List<Bono> getALl() {
        return BonoPersistenceMapper.bonoEntitiesToBonoModels(bonoRepository.findAll());
    }

    @Override
    public Bono create(Bono bono) throws Exception {
        if (bono == null) {
            throw new BonoNotFoundException();
        }
        return BonoPersistenceMapper.bonoEntityToBonoModel(bonoRepository.save(BonoPersistenceMapper.bonoModelToBonoEntity(bono)));
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (bonoRepository.findById(id).isEmpty()){
            throw new BonoNotFoundException();
        }
        bonoRepository.deleteById(id);
    }
}
