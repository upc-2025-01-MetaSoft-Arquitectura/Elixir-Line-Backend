package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;


public record IncidentResource(
        Long id,
        Long taskId,
        String description,
        String imageUrl,
        String createdAt,
        String updatedAt
) {
}
