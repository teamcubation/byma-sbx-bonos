package com.services.bond.app.infrastructure.adapter.in.web;

import com.services.bond.app.configuration.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService configService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getConfiguration(){
        Map<String, Object> config = new HashMap<>();
        config.put("applicationName", configService.getApplicationName());
        config.put("portService", configService.getServerPort());
        config.put("databaseUrl", configService.getDatabaseUrl());
        config.put("databaseUsername", configService.getDatabaseUsername());
        config.put("h2ConsoleEnabled", configService.isH2ConsoleEnabled());
        config.put("loggingLevel", configService.getLoggingLevel());

        return ResponseEntity.ok(config);
    }
}
