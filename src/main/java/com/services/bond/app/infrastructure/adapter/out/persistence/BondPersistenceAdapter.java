package com.services.bond.app.infrastructure.adapter.out.persistence;

import com.services.bond.app.application.port.out.BondOutPort;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.domain.model.exception.BondNotFoundException;
import com.services.bond.app.infrastructure.adapter.out.persistence.mapper.BondPersistenceMapper;
import com.services.bond.app.infrastructure.adapter.out.persistence.repository.BondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BondPersistenceAdapter implements BondOutPort {

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
