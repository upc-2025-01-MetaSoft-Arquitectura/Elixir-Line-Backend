package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.VinegrowerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.ActivateVinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateVinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.DeleteVinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByVinegrowerIdByEmployeeStatusQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByVinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllVinegrowersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetVinegrowerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower.VinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower.VinegrowerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.*;
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

    @GetMapping
    public ResponseEntity<List<VinegrowerResource>> getAll() {
        List<Vinegrower> vinegrowers = queryService.handle(new GetAllVinegrowersQuery());
        List<VinegrowerResource> resources = vinegrowers.stream()
                .map(VinegrowerResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{vinegrowerId}/field-workers")
    public ResponseEntity<List<FieldWorkerResource>> getFieldWorkersByVinegrowerId(@PathVariable Long vinegrowerId) {
        List<FieldWorker> fieldWorkers = queryService.handle(
                new GetAllFieldWorkersByVinegrowerIdQuery(vinegrowerId)
        );

        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{vinegrowerId}/field-workers/status/{status}")
    public ResponseEntity<List<FieldWorkerResource>> getFieldWorkersByVinegrowerIdAndStatus(@PathVariable Long vinegrowerId, @PathVariable EmployeeStatus status) {
        List<FieldWorker> fieldWorkers = queryService.handle(
                new GetAllFieldWorkersByVinegrowerIdByEmployeeStatusQuery(vinegrowerId, status)
        );

        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{vinegrowerId}")
    public ResponseEntity<VinegrowerResource> getById(@PathVariable Long vinegrowerId) {
        return queryService.handle(new GetVinegrowerByIdQuery(vinegrowerId))
                .map(VinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VinegrowerResource> createVinegrower(@RequestBody @Valid CreateVinegrowerResource resource) {
        try {
            CreateVinegrowerCommand command = CreateVinegrowerCommandFromResourceAssembler.toCommandFromResource(resource);
            Vinegrower vinegrower = commandService.handle(command)
                    .orElseThrow(() -> new VinegrowerNotBeCreated(""));
            VinegrowerResource vinegrowerResource = VinegrowerResourceAssembler.toResource(vinegrower);
            return ResponseEntity.status(HttpStatus.CREATED).body(vinegrowerResource);
        } catch (VinegrowerNotBeCreated e) {
            throw new VinegrowerNotBeCreated(e.getMessage());
        }
    }

    @PutMapping(value = "/{vinegrowerId}")
    public ResponseEntity<VinegrowerResource> update(@PathVariable Long vinegrowerId, @RequestBody @Valid UpdateVinegrowerResource resource) {
        var command = UpdateVinegrowerCommandFromResourceAssembler.toCommandFromResource(vinegrowerId, resource);
        var updatedVinegrower = commandService.update(command);
        return updatedVinegrower
                .map(VinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    @PatchMapping(value = "/{vinegrowerId}")
    public ResponseEntity<VinegrowerResource> partialUpdate(@PathVariable Long vinegrowerId, @RequestBody @Valid UpdateVinegrowerResource resource) {
        var command = UpdateVinegrowerCommandFromResourceAssembler
                .toCommandFromResource(vinegrowerId, resource);

        var updatedVinegrower = commandService.updatePartial(command);

        return updatedVinegrower
                .map(VinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    */

    @DeleteMapping(value = "/{vinegrowerId}/deactivate")
    public ResponseEntity<Void> deactivateVinegrower(@PathVariable Long vinegrowerId) {
        commandService.logicallyDelete(new DeleteVinegrowerCommand(vinegrowerId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{vinegrowerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVinegrower(@PathVariable Long vinegrowerId) {
        commandService.physicallyDelete(new DeleteVinegrowerCommand(vinegrowerId));
    }

    @PatchMapping(value = "/{vinegrowerId}/activate")
    public ResponseEntity<VinegrowerResource> activateVinegrower(@PathVariable Long vinegrowerId) {
        var activatedVinegrower = commandService.activate(new ActivateVinegrowerCommand(vinegrowerId));
        return activatedVinegrower
                .map(VinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
