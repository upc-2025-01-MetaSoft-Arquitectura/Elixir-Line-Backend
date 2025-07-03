package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record CreateTaskResource(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Long winegrowerId,
        Long batchId,
        List<Long> suppliesIds
) {
}
