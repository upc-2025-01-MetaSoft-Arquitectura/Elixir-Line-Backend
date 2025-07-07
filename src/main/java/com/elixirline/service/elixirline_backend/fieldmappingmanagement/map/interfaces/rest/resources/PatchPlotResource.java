package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.Coordinate;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.PlotType;

import java.util.List;

public record PatchPlotResource(
        PlotType type,
        List<Coordinate> path,
        String label,
        Long wineBatchId
) {
}
