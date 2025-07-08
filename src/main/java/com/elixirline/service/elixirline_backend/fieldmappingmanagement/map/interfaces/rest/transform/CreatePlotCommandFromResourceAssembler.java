package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.CreatePlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.Coordinate;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.PlotType;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources.CreatePlotResource;

import java.util.List;

public class CreatePlotCommandFromResourceAssembler {
    public static CreatePlotCommand toCommandFromResource(CreatePlotResource resource) {
        List<Coordinate> coordinates = resource.path().stream()
                .map(p -> new Coordinate(p.lat(), p.lng()))
                .toList();

        return new CreatePlotCommand(
                PlotType.valueOf(resource.type()),
                coordinates,
                resource.label(),
                resource.batchId(),
                resource.winegrowerId()
        );
    }
}
