package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.services;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.aggregates.Plot;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.CreatePlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.DeletePlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.PatchPlotCommand;

import java.util.Optional;

public interface PlotCommandService {
    Optional<Plot> handle(CreatePlotCommand command);
    void handle(DeletePlotCommand command);
    Optional<Plot> handle(PatchPlotCommand command);
}
