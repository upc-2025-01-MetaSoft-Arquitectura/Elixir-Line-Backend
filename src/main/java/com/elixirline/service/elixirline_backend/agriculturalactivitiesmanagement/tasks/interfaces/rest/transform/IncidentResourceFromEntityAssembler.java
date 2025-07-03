package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Incident;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.IncidentResource;

public class IncidentResourceFromEntityAssembler {
    public static IncidentResource toResourceFromEntity(Incident incident) {
        return new IncidentResource(
                incident.getId(),
                incident.getTaskId(),
                incident.getDescription(),
                incident.getImageUrl(),
                incident.getCreatedAt().toString(),
                incident.getUpdatedAt().toString()
        );
    }
}
