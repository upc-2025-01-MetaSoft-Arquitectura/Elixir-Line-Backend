package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.DeletePlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.queries.GetAllPlotsQuery;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.services.PlotCommandService;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.services.PlotQueryService;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources.CreatePlotResource;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources.PatchPlotResource;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources.PlotResource;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.transform.CreatePlotCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.transform.PatchPlotCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.transform.PlotResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/plot")
@Tag(name = "Plot", description = "Plot Management Endpoints")
public class PlotController {
    private final PlotQueryService plotQueryService;
    private final PlotCommandService plotCommandService;
    PlotController(PlotQueryService plotQueryService,  PlotCommandService plotCommandService) {
        this.plotQueryService = plotQueryService;
        this.plotCommandService = plotCommandService;
    }

    @GetMapping
    public ResponseEntity<List<PlotResource>> getAllPlots() {
        var getAllPlotsQuery = new GetAllPlotsQuery();
        var plots = plotQueryService.handle(getAllPlotsQuery);
        var plotResources = plots.stream().map(PlotResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(plotResources);
    }

    @PostMapping
    public ResponseEntity<PlotResource> createPlot(@RequestBody CreatePlotResource resource) {
        var command = CreatePlotCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = plotCommandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();
        var response = PlotResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{plotId}")
    public ResponseEntity<?> deletePlot(@PathVariable Long plotId) {
        var deletePlotCommand = new DeletePlotCommand(plotId);
        plotCommandService.handle(deletePlotCommand);
        return ResponseEntity.ok("Deleted Plot");
    }

    @PatchMapping("/{plotId}")
    public ResponseEntity<PlotResource> patchPlot(@PathVariable Long plotId, @RequestBody PatchPlotResource resource) {
        var command = PatchPlotCommandFromResourceAssembler.toCommandFromResource(plotId, resource);
        var updated = plotCommandService.handle(command);
        if (updated.isEmpty()) return ResponseEntity.notFound().build();
        var updatedResource = PlotResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(updatedResource);
    }
}
