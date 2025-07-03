package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

public record EvidenceResource(Long id,
                               Long taskId,
                               String description,
                               Integer progressPercentage,
                               String imageUrl,
                               String createdAt,
                               String updatedAt) {
}
