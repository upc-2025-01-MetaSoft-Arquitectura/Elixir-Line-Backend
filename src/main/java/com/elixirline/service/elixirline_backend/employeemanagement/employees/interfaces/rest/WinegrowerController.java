package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.WinegrowerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.ActivateWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.DeleteWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByWinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllWinegrowersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetWinegrowerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerQueryService;
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
@RequestMapping(value = "/api/v1/winegrowers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Winegrowers", description = "Winegrower Management Endpoints")
public class WinegrowerController {
    private final WinegrowerCommandService commandService;
    private final WinegrowerQueryService queryService;

    public WinegrowerController(WinegrowerCommandService commandService, WinegrowerQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<WinegrowerResource>> getAll() {
        List<Winegrower> winegrowers = queryService.handle(new GetAllWinegrowersQuery());
        List<WinegrowerResource> resources = winegrowers.stream()
                .map(WinegrowerResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{winegrowerId}/field-workers")
    public ResponseEntity<List<FieldWorkerResource>> getFieldWorkersByWinegrowerId(@PathVariable Long winegrowerId) {
        List<FieldWorker> fieldWorkers = queryService.handle(
                new GetAllFieldWorkersByWinegrowerIdQuery(winegrowerId)
        );

        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{winegrowerId}/field-workers/status/{status}")
    public ResponseEntity<List<FieldWorkerResource>> getFieldWorkersByWinegrowerIdAndStatus(@PathVariable Long winegrowerId, @PathVariable EmployeeStatus status) {
        List<FieldWorker> fieldWorkers = queryService.handle(
                new GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery(winegrowerId, status)
        );

        List<FieldWorkerResource> resources = fieldWorkers.stream()
                .map(FieldWorkerResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{winegrowerId}")
    public ResponseEntity<WinegrowerResource> getById(@PathVariable Long winegrowerId) {
        return queryService.handle(new GetWinegrowerByIdQuery(winegrowerId))
                .map(WinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WinegrowerResource> createWinegrower(@RequestBody @Valid CreateWinegrowerResource resource) {
        try {
            CreateWinegrowerCommand command = CreateWinegrowerCommandFromResourceAssembler.toCommandFromResource(resource);
            Winegrower winegrower = commandService.handle(command)
                    .orElseThrow(() -> new WinegrowerNotBeCreated(""));
            WinegrowerResource winegrowerResource = WinegrowerResourceAssembler.toResource(winegrower);
            return ResponseEntity.status(HttpStatus.CREATED).body(winegrowerResource);
        } catch (WinegrowerNotBeCreated e) {
            throw new WinegrowerNotBeCreated(e.getMessage());
        }
    }

    @PutMapping(value = "/{winegrowerId}")
    public ResponseEntity<WinegrowerResource> update(@PathVariable Long winegrowerId, @RequestBody @Valid UpdateWinegrowerResource resource) {
        var command = UpdateWinegrowerCommandFromResourceAssembler.toCommandFromResource(winegrowerId, resource);
        var updatedWinegrower = commandService.update(command);
        return updatedWinegrower
                .map(WinegrowerResourceAssembler::toResource)
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

    @DeleteMapping(value = "/{winegrowerId}/deactivate")
    public ResponseEntity<Void> deactivateWinegrower(@PathVariable Long winegrowerId) {
        commandService.logicallyDelete(new DeleteWinegrowerCommand(winegrowerId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{winegrowerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWinegrower(@PathVariable Long winegrowerId) {
        commandService.physicallyDelete(new DeleteWinegrowerCommand(winegrowerId));
    }

    @PatchMapping(value = "/{winegrowerId}/activate")
    public ResponseEntity<WinegrowerResource> activateWinegrower(@PathVariable Long winegrowerId) {
        var activatedWinegrower = commandService.activate(new ActivateWinegrowerCommand(winegrowerId));
        return activatedWinegrower
                .map(WinegrowerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
