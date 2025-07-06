package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.interfaces.rest.resources;

import java.util.List;

public record CreatePlotResource(
        String type,
        List<CoordinateResource> path,
        String label,
        Long wineBatchId
) {
}
