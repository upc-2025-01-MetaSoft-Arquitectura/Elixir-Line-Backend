package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.Coordinate;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.PlotType;

import java.util.List;

public record CreatePlotCommand(
        PlotType type,
        List<Coordinate> path,
        String label,
        Long batchId,
        Long winegrowerId
) {
}
