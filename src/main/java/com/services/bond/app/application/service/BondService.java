package com.services.bond.app.application.service;

import com.services.bond.app.application.port.in.BondInPort;
import com.services.bond.app.application.port.out.BondOutPort;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BondService implements BondInPort {

    private final BondOutPort bondOutPort;

    @Override
    public Bond create(Bond bond) throws Exception {
        if (bondOutPort.findById(bond.getId()).isPresent()) {
            throw new Exception("Bond already exists");
        }
        return bondOutPort.create(bond);
    }

    @Override
    public Bond findByID(long id) throws BondNotFoundException {
        return bondOutPort.findById(id).orElseThrow(BondNotFoundException::new);
    }

    @Override
    public Bond update(long id, Bond bond) throws Exception {
        return bondOutPort.findById(id).map(bond1 -> {
            bond1.setName(bond.getName());
            bond1.setPrice(bond.getPrice());
            bond1.setInterestRate(bond.getInterestRate());
            return bondOutPort.create(bond1);
        }).orElseThrow(BondNotFoundException::new);
    }

    @Override
    public void delete(long id) throws Exception {
        if (bondOutPort.findById(id).isEmpty())
            throw new BondNotFoundException();
        bondOutPort.deleteById(id);
    }

    @Override
    public Iterable<Bond> getAll() {
        return bondOutPort.getAll();
    }
}
