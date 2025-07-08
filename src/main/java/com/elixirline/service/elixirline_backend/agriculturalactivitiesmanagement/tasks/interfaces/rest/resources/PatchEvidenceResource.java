package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record PatchEvidenceResource(
        Long taskId,
        String description,
        Integer progressPercentage,
        MultipartFile imageFile
) {
}
