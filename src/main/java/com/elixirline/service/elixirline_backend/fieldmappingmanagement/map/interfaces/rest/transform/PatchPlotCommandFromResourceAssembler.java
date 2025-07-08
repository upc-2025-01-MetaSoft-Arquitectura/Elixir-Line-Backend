package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.commands.PatchPlotCommand;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.Coordinate;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.PlotType;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources.PatchPlotResource;

import java.util.List;

public class PatchPlotCommandFromResourceAssembler {
//    public static PatchPlotCommand toCommandFromResource(Long plotId, PatchPlotResource resource) {
//        List<Coordinate> coordinates = null;
//        if (resource.path() != null) {
//            coordinates = resource.path().stream()
//                    .map(p -> new Coordinate(p.lat(), p.lng()))
//                    .toList();
//        }
//        PlotType type = null;
//        try {
//            if (resource.type() != null)
//                type = PlotType.valueOf(resource.type().toUpperCase());
//        } catch (IllegalArgumentException ex) {
//            throw new IllegalArgumentException("Tipo de parcela inválido: " + resource.type());
//        }
//        return new PatchPlotCommand(
//                plotId,
//                type,
//                coordinates,
//                resource.label(),
//                resource.wineBatchId()
//        );
//    }

    public static PatchPlotCommand toCommandFromResource(Long plotId, PatchPlotResource resource) {
        return new PatchPlotCommand(
                plotId,
                resource.type(),
                resource.path(),
                resource.label(),
                resource.batchId()
        );
    }
}
