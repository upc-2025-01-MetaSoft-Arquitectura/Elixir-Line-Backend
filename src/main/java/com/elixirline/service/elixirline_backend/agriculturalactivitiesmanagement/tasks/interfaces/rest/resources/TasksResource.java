package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record TasksResource(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String assignedTo,
        Long batchId,
        List<Long> suppliesIds
) {
}
