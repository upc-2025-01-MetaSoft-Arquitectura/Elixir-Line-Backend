package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.aggregates.Plot;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.queries.GetAllPlotsQuery;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.services.PlotQueryService;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.infrastructure.persistence.jpa.repositories.PlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotQueryServiceImpl implements PlotQueryService {
    private final PlotRepository plotRepository;
    public PlotQueryServiceImpl(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    @Override
    public List<Plot> handle(GetAllPlotsQuery query) { return plotRepository.findAll(); }
}
