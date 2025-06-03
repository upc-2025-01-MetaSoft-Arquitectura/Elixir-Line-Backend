package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateVinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllVinegrowersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetVinegrowerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower.VinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower.VinegrowerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.CreateVinegrowerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.VinegrowerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.CreateVinegrowerCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.VinegrowerResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/vinegrowers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Vinegrowers", description = "Vinegrower Management Endpoints")
public class VinegrowerController {

    private final VinegrowerCommandService commandService;
    private final VinegrowerQueryService queryService;

    public VinegrowerController(VinegrowerCommandService commandService, VinegrowerQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<VinegrowerResource> createVinegrower(@RequestBody @Valid CreateVinegrowerResource resource) {
        CreateVinegrowerCommand command = CreateVinegrowerCommandFromResourceAssembler.toCommandFromResource(resource);
        Vinegrower vinegrower = commandService.handle(command);
        VinegrowerResource vinegrowerResource = VinegrowerResourceAssembler.toResource(vinegrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(vinegrowerResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VinegrowerResource> getById(@PathVariable Long id) {
        return queryService.handle(new GetVinegrowerByIdQuery(id))
                .map(VinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VinegrowerResource>> getAll() {
        List<Vinegrower> vinegrowers = queryService.handle(new GetAllVinegrowersQuery());
        List<VinegrowerResource> resources = vinegrowers.stream()
                .map(VinegrowerResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
