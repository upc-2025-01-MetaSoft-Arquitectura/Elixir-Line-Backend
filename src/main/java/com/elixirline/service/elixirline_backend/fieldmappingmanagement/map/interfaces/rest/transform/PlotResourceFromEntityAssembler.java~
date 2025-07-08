package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.aggregates.Plot;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources.CoordinateResource;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources.PlotResource;

import java.util.stream.Collectors;

public class PlotResourceFromEntityAssembler {
    public static PlotResource toResourceFromEntity(Plot entity) {
        var coordinates = entity.getPath().stream()
                .map(c -> new CoordinateResource(c.getLat(), c.getLng()))
                .collect(Collectors.toList());

        return new PlotResource(
                entity.getId(),
                entity.getType().name(),
                coordinates,
                entity.getLabel(),
                entity.getWineBatchId()
        );
    }
}
