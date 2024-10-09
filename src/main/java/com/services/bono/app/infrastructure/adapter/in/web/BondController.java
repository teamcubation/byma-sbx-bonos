package com.services.bono.app.infrastructure.adapter.in.web;

import com.services.bono.app.application.port.in.BonoServicePort;
import com.services.bono.app.domain.model.Bono;
import com.services.bono.app.infrastructure.adapter.in.web.dto.BonoDTO;
import com.services.bono.app.infrastructure.adapter.in.web.mapper.BonoMapper;
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
    private static final String REGISTRANDO_BONO = "registrando bono...";
    private static final String BONO_CREADO = "bono creado: ";
    private static final String OBTENIENDO_TODOS_LOS_BONOS = "obteniendo todos los bonos...";
    private static final String BONO_POR_ID = "obteniendo bono por ID...";
    private static final String ELIMINANDO_BONO = "eliminando bono: ";
    private static final String ACTUALIZANDO_BONO = "actualizando bono...";

    @Autowired
    private BonoServicePort bonoServicePort;

    public BondController(BonoServicePort bonoServicePort) {
        this.bonoServicePort = bonoServicePort;
    }

    @PostMapping("/")
    public ResponseEntity<?> registrar(@RequestBody @Valid BonoDTO bonoDTO) {
        log.info(REGISTRANDO_BONO);
        Bono bono = BonoMapper.bonoDTOToBono(bonoDTO);
        bonoServicePort.registrarBono(bono);
        log.info(BONO_CREADO + bonoDTO);
        return new ResponseEntity<BonoDTO>(bonoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> obtenerTodos() {
        log.info(OBTENIENDO_TODOS_LOS_BONOS);
        return ResponseEntity.ok(bonoServicePort.consultarTodosLosBonos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorID(@PathVariable Long id) {
        log.info(BONO_POR_ID);
        return ResponseEntity.ok(bonoServicePort.buscarBonoPorID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        log.info(ELIMINANDO_BONO + id);
        bonoServicePort.eliminarBono(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid BonoDTO bonoDTO) {
        log.info(ACTUALIZANDO_BONO);
        Bono bono = BonoMapper.bonoDTOToBono(bonoDTO);
        return ResponseEntity.ok(bonoServicePort.actualizarBono(id, bono));
    }
}