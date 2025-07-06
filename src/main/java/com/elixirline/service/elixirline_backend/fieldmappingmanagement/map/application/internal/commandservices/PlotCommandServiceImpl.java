package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.aggregates.Plot;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.CreatePlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.DeletePlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.PatchPlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.services.PlotCommandService;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.infrastructure.persistence.jpa.repositories.PlotRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlotCommandServiceImpl implements PlotCommandService {
    private final PlotRepository plotRepository;
    PlotCommandServiceImpl(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }
    @Override
    public Optional<Plot> handle(CreatePlotCommand command) {
        Plot plot = new Plot(
                command.type(),
                command.path(),
                command.label(),
                command.wineBatchId()
        );
        return Optional.of(plotRepository.save(plot));
    }

    @Override
    public void handle(DeletePlotCommand command) {
        plotRepository.deleteById(command.plotId());
    }

    @Override
    public Optional<Plot> handle(PatchPlotCommand command) {
        var existing = plotRepository.findById(command.plotId());
        if (existing.isEmpty()) return Optional.empty();
        var plot = existing.get();
        plot.updateInformation(
                command.type(),
                command.path(),
                command.label(),
                command.wineBatchId()
        );
        return Optional.of(plotRepository.save(plot));
    }
}
