package com.services.bono.app.infrastructure.adapter.out.persistence;

import com.services.bono.app.application.port.out.BondRepositoryPort;
import com.services.bono.app.domain.model.Bond;
import com.services.bono.app.domain.model.exception.BondNotFoundException;
import com.services.bono.app.infrastructure.adapter.out.persistence.mapper.BondPersistenceMapper;
import com.services.bono.app.infrastructure.adapter.out.persistence.repository.BondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BondPersistenceAdapter implements BondRepositoryPort {

    private final BondRepository bondRepository;

    @Override
    public Bond findById(long id) throws Exception {
        return bondRepository.findById(id)
                .map(BondPersistenceMapper::bondEntityToBondModel)
                .orElseThrow(BondNotFoundException::new);
    }

    @Override
    public List<Bond> getAll() {
        return BondPersistenceMapper.bondEntitiesToBondModels(bondRepository.findAll());
    }

    @Override
    public Bond create(Bond bond) throws Exception {
        if (bond == null) {
            throw new BondNotFoundException();
        }
        return BondPersistenceMapper.bondEntityToBondModel(bondRepository.save(BondPersistenceMapper.bondModelToBondEntity(bond)));
    }

    @Override
    public void deleteById(long id) throws Exception {
        if (bondRepository.findById(id).isEmpty()){
            throw new BondNotFoundException();
        }
        bondRepository.deleteById(id);
    }
}
