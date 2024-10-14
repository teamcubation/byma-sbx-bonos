package com.services.bond.app.application.service;

import com.services.bond.app.application.port.in.BondInPort;
import com.services.bond.app.application.port.out.BondOutPort;
import com.services.bond.app.application.service.exception.BondDuplicateException;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import com.services.bond.app.application.service.exception.BondDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BondService implements BondInPort {

    private final BondOutPort bondOutPort;

    @Override
    public Bond create(Bond bond) throws BondDuplicateException {
        if (bondOutPort.existsByNameIgnoreCase(bond.getName())) {
            throw new BondDuplicateException();
        }
        return bondOutPort.save(bond);
    }

    @Override
    public Bond findById(long id) throws BondNotFoundException {
        return bondOutPort.findById(id).orElseThrow(BondNotFoundException::new);
    }

    @Override
    public Bond update(long id, Bond bond) throws BondNotFoundException, BondDuplicateException {
        Bond existingBond = bondOutPort.findById(id).orElseThrow(BondNotFoundException::new);

        if (!existingBond.getName().equals(bond.getName()) && bondOutPort.existsByNameIgnoreCase(bond.getName())) {
            throw new BondDuplicateException();
        }

        if(bond.getPrice() != null && bond.getPrice() > 0) {
            existingBond.setPrice(bond.getPrice());
        }
        if(bond.getInterestRate() != null && bond.getInterestRate() > 0) {
            existingBond.setInterestRate(bond.getInterestRate());
        }
        if(bond.getName() != null && !bond.getName().isEmpty()){
            existingBond.setName(bond.getName());
        }

        return bondOutPort.save(existingBond);
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
