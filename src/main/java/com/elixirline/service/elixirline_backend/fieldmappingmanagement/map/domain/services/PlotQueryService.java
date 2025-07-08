package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.services;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.aggregates.Plot;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.queries.GetAllPlotsByWinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.queries.GetAllPlotsQuery;

import java.util.List;

public interface PlotQueryService {
    List<Plot> handle(GetAllPlotsQuery query);
    List<Plot> handle(GetAllPlotsByWinegrowerIdQuery query);
}
