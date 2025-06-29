package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import java.time.LocalDate;
import java.util.List;

public record UpdateTaskCommand(
        Long taskId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String assignedTo,
        Long batchId,
        List<Long> suppliesIds
) {
}
