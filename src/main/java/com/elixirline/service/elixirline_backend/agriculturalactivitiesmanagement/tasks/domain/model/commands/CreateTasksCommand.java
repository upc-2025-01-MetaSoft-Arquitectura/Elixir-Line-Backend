package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import java.time.LocalDate;
import java.util.List;

public record CreateTasksCommand(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Long winegrowerId,
        Long batchId,
        List<Long> suppliesIds
) {
}
