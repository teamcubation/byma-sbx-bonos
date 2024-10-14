package com.services.bond.app.application.service;

import com.services.bond.app.application.port.in.BondInPort;
import com.services.bond.app.application.port.out.BondOutPort;
import com.services.bond.app.application.service.exception.BondDuplicateException;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BondService implements BondInPort {

    private final BondOutPort bondOutPort;

    @Override
    public Bond create(Bond bond) throws BondDuplicateException {
        if (bondOutPort.findById(bond.getId()).isPresent()) {
            throw new BondDuplicateException();
        }
        return bondOutPort.create(bond);
    }

    @Override
    public Bond findById(long id) throws BondNotFoundException {
        return bondOutPort.findById(id).orElseThrow(BondNotFoundException::new);
    }

    @Override
    public Bond update(long id, Bond bond) throws BondNotFoundException, BondDuplicateException {
        return bondOutPort.findById(id).map(bond1 -> {
            bond1.setName(bond.getName());
            bond1.setPrice(bond.getPrice());
            bond1.setInterestRate(bond.getInterestRate());
            return bondOutPort.create(bond1);
        }).orElseThrow(BondNotFoundException::new);
    }

    @Override
    public void delete(long id) throws BondNotFoundException {
        if (bondOutPort.findById(id).isEmpty())
            throw new BondNotFoundException();
        bondOutPort.deleteById(id);
    }

    @Override
    public List<Bond> getAll() {
        return bondOutPort.getAll();
    }
}
