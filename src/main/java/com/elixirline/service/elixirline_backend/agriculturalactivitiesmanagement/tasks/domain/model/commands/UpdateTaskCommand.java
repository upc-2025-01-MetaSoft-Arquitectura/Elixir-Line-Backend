package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskStatus;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;

import java.time.LocalDate;
import java.util.List;

public record UpdateTaskCommand(
        Long taskId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Long winegrowerId,
        String fieldWorkerName,
        Long batchId,
        List<Long> suppliesIds,
        Integer progressPercentage,
        TaskStatus status,
        TaskType type
) {
}
