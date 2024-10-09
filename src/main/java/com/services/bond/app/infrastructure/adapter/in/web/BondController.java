package com.services.bond.app.infrastructure.adapter.in.web;

import com.services.bond.app.application.port.in.BondInPort;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.infrastructure.adapter.in.web.dto.BondDTO;
import com.services.bond.app.infrastructure.adapter.in.web.mapper.BondMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bono")
public class BondController {
    private static final Logger log = LoggerFactory.getLogger(BondController.class);
    private static final String REGISTERING_BOND = "registering bond...";
    private static final String BOND_CREATED = "bond created: ";
    private static final String GETTING_ALL_BONDS = "getting all bonds...";
    private static final String BOND_BY_ID = "getting bond by ID...";
    private static final String DELETING_BOND = "deleting bond: ";
    private static final String UPDATING_BOND = "updating bond...";

    @Autowired
    private BondInPort bondInPort;

    public BondController(BondInPort bondInPort) {
        this.bondInPort = bondInPort;
    }

    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody @Valid BondDTO bondDTO) {
        log.info(REGISTERING_BOND);
        Bond bond = BondMapper.bondDTOToBond(bondDTO);
        bondInPort.register(bond);
        log.info(BOND_CREATED + bondDTO);
        return new ResponseEntity<BondDTO>(bondDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        log.info(GETTING_ALL_BONDS);
        return ResponseEntity.ok(bondInPort.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable long id) {
        log.info(BOND_BY_ID);
        return ResponseEntity.ok(bondInPort.getByID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        log.info(DELETING_BOND + id);
        bondInPort.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Valid BondDTO bondDTO) {
        log.info(UPDATING_BOND);
        Bond bond = BondMapper.bondDTOToBond(bondDTO);
        return ResponseEntity.ok(BondMapper.bondToBondDTO(bondInPort.update(id, bond)));
    }
}