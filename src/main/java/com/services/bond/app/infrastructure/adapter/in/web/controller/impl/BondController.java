package com.services.bond.app.infrastructure.adapter.in.web.controller.impl;

import com.services.bond.app.application.port.in.BondInPort;
import com.services.bond.app.application.service.exception.BondNotFoundException;
import com.services.bond.app.domain.model.Bond;
import com.services.bond.app.infrastructure.adapter.in.web.controller.ApiBond;
import com.services.bond.app.infrastructure.adapter.in.web.dto.request.BondRequest;
import com.services.bond.app.infrastructure.adapter.in.web.dto.response.BondResponse;
import com.services.bond.app.infrastructure.adapter.in.web.mapper.BondMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/bonds")
public class BondController implements ApiBond {
    private static final String REGISTERING_BOND = "registering bond...";
    private static final String BOND_CREATED = "bond created: ";
    private static final String GETTING_ALL_BONDS = "getting all bonds...";
    private static final String BOND_BY_ID = "getting bond by ID...";
    private static final String DELETING_BOND = "deleting bond: ";
    private static final String UPDATING_BOND = "updating bond...";

    private final BondInPort bondInPort;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody @Valid BondRequest bondRequest) {
        log.info(REGISTERING_BOND);
        Bond bond = BondMapper.bondRequestToBond(bondRequest);
        bondInPort.create(bond);
        log.info(BOND_CREATED + bondRequest);
        return new ResponseEntity<BondResponse>(BondMapper.bondToBondResponse(bond), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        log.info(GETTING_ALL_BONDS);
        return ResponseEntity.ok(bondInPort.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) throws BondNotFoundException {
        log.info(BOND_BY_ID);
        return ResponseEntity.ok(bondInPort.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        log.info(DELETING_BOND + id);
        bondInPort.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody BondRequest bondRequest) {
        log.info(UPDATING_BOND);
        Bond bond = BondMapper.bondRequestToBond(bondRequest);
        return ResponseEntity.ok(BondMapper.bondToBondResponse(bondInPort.update(id, bond)));
    }
}