package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Incident;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.IncidentWithTaskInfoResource;

public class IncidentWithTaskInfoResourceAssembler {
    public static IncidentWithTaskInfoResource toResource(Incident incident, Tasks task) {
        return new IncidentWithTaskInfoResource(
                incident.getId(),
                task.getId(),
                task.getTitle(),
                task.getBatchId(),
                task.getWinegrowerId(),
                task.getType().name(),
                incident.getDescription(),
                incident.getImageUrl(),
                incident.getCreatedAt().toString()
        );
    }
}
