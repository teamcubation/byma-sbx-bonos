package com.services.bond.app.infrastructure.adapter.out.persistence;

import com.services.bond.app.application.port.out.BondOutPort;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.domain.model.exception.BondNotFoundException;
import com.services.bond.app.infrastructure.adapter.out.persistence.mapper.BondPersistenceMapper;
import com.services.bond.app.infrastructure.adapter.out.persistence.repository.BondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BondPersistenceAdapter implements BondOutPort {

    private final BondRepository bondRepository;


    @Override
    public Optional<Bond> findById(long id) {
        return bondRepository.findById(id)
                .map(BondPersistenceMapper::bondEntityToBondModel);
    }

    @Override
    public List<Bond> getAll() {
        return BondPersistenceMapper.bondEntitiesToBondModels(bondRepository.findAll());
    }

    @Override
    public Bond save(Bond bond) throws BondNotFoundException {
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

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return bondRepository.existsByNameIgnoreCase(name);
    }
}
