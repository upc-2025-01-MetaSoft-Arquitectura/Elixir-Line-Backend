package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.FieldWorkerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.ActivateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.DeleteFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetFieldWorkerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker.FieldWorkerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker.FieldWorkerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.CreateFieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.FieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.PartialUpdateFieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateFieldWorkerResource;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.CreateFieldWorkerCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.FieldWorkerResourceAssembler;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.PartialUpdateFieldWorkerCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform.UpdateFieldWorkerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/fieldworkers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Field Workers", description = "Field Worker Management Endpoints")
public class FieldWorkerController {
    private final FieldWorkerCommandService commandService;
    private final FieldWorkerQueryService queryService;

    public FieldWorkerController(FieldWorkerCommandService commandService, FieldWorkerQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<FieldWorkerResource>> getAll() {
        List<FieldWorker> fieldWorkers = queryService.handle(new GetAllFieldWorkersQuery());
        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{fieldWorkerId}")
    public ResponseEntity<FieldWorkerResource> getById(@PathVariable Long fieldWorkerId) {
        return queryService.handle(new GetFieldWorkerByIdQuery(fieldWorkerId))
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FieldWorkerResource> createFieldWorker(@RequestBody @Valid CreateFieldWorkerResource resource) {
        try {
            CreateFieldWorkerCommand command = CreateFieldWorkerCommandFromResourceAssembler.toCommandFromResource(resource);
            FieldWorker fieldWorker = commandService.handle(command)
                    .orElseThrow(() -> new FieldWorkerNotBeCreated(" Field Worker Command Service Error"));
            FieldWorkerResource fieldWorkerResource = FieldWorkerResourceAssembler.toResource(fieldWorker);
            return ResponseEntity.status(HttpStatus.CREATED).body(fieldWorkerResource);
        } catch (Exception e) {
            throw new FieldWorkerNotBeCreated(e.getMessage());
        }
    }

    @PutMapping(value = "/{fieldWorkerId}")
    public ResponseEntity<FieldWorkerResource> update(@PathVariable Long fieldWorkerId, @RequestBody @Valid UpdateFieldWorkerResource resource) {
        var command = UpdateFieldWorkerCommandFromResourceAssembler.toCommandFromResource(fieldWorkerId, resource);
        var updatedFieldWorker = commandService.update(command);
        return updatedFieldWorker
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    @PatchMapping(value = "/{fieldWorkerId}")
    public ResponseEntity<FieldWorkerResource> partialUpdate(@PathVariable Long fieldWorkerId, @RequestBody @Valid PartialUpdateFieldWorkerResource resource) {
        if (resource.hasNoUpdates()) {
            return ResponseEntity.badRequest().build();
        }

        var command = PartialUpdateFieldWorkerCommandFromResourceAssembler
                .toCommandFromResource(fieldWorkerId, resource);

        var updatedFieldWorker = commandService.updatePartial(command);

        return updatedFieldWorker
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    */

    @DeleteMapping(value = "/{fieldWorkerId}/deactivate")
    public ResponseEntity<Void> deactivateFieldWorker(@PathVariable Long fieldWorkerId) {
        commandService.logicallyDelete(new DeleteFieldWorkerCommand(fieldWorkerId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{fieldWorkerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFieldWorker(@PathVariable Long fieldWorkerId) {
        commandService.physicallyDelete(new DeleteFieldWorkerCommand(fieldWorkerId));
    }

    @PatchMapping(value = "/{fieldWorkerId}/activate")
    public ResponseEntity<FieldWorkerResource> activateFieldWorker(@PathVariable Long fieldWorkerId) {
        var activatedFieldWorker = commandService.activate(new ActivateFieldWorkerCommand(fieldWorkerId));
        return activatedFieldWorker
                .map(FieldWorkerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

