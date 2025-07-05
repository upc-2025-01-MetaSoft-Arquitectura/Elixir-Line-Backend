package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.TasksResource;

public class TasksResourceFromEntityAssembler {
    public static TasksResource toResourceFromEntity(Tasks task) {
        return new TasksResource(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getWinegrowerId(),
                task.getFieldWorkerName(),
                task.getBatchId(),
                task.getType(),
                task.getSuppliesIds()
        );
    }
}
