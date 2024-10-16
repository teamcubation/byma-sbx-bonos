package com.services.bond.app.infrastructure.adapter.in.web.controller;

import com.services.bond.app.infrastructure.adapter.in.web.dto.request.BondRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Tag(name = "Config", description = "Api for managing configurations")
public interface ApiConfig {

    @Operation(summary = "get info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Config created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Map<String, Object>> getConfiguration();



}
