package com.services.bond.app.infrastructure.adapter.in.web.controller;

import com.services.bond.app.infrastructure.adapter.in.web.dto.request.BondRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Bond", description = "Api for managing bonds")

public interface ApiBond {
    @Operation(summary = "Create bond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bond created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> register(@RequestBody @Valid BondRequest bondRequest);

    @Operation(summary = "Get all bonds")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bond found"),
            @ApiResponse(responseCode = "500", description = "Error: Internal server error")
    })
    ResponseEntity<?> getAll();

    @Operation(summary = "Get a bond by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bond found"),
            @ApiResponse(responseCode = "404", description = "Error: Bond not found"),
            @ApiResponse(responseCode = "500", description = "Error: Internal server error")
    })
    ResponseEntity<?> getById(@PathVariable long id);

    @Operation(summary = "Update a bond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bond updated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> update(@PathVariable long id, @RequestBody BondRequest bondRequest);

    @Operation(summary = "Delete a bond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bond deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Error: Bond not found"),
            @ApiResponse(responseCode = "500", description = "Error: Internal server error")
    })
    ResponseEntity<?> delete(@PathVariable long id);

}
