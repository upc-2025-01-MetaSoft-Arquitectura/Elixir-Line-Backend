package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskStatus;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;

import java.time.LocalDate;
import java.util.List;

public record TasksResource(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Long winegrowerId,
        String fieldWorkerName,
        Long fieldWorkerId,
        Long batchId,
        Integer progressPercentage,
        TaskStatus status,
        TaskType type,
        List<Long> suppliesIds
) {
}
