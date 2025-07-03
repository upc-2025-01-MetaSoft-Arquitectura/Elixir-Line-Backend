package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.EvidenceResource;

public class EvidenceResourceFromEntityAssembler {
    public static EvidenceResource toResourceFromEntity(Evidence evidence) {
        return new EvidenceResource(
                evidence.getId(),
                evidence.getTaskId(),
                evidence.getDescription(),
                evidence.getProgressPercentage(),
                evidence.getImageUrl(),
                evidence.getCreatedAt().toString(),
                evidence.getUpdatedAt().toString()
        );
    }
}
