package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

public record IncidentWithTaskInfoResource(
        Long incidentId,
        Long taskId,
        String taskTitle,
        Long batchId,
        Long winegrowerId,
        String taskType,
        String incidentDescription,
        String imageUrl,
        String createdAt
) {
}
