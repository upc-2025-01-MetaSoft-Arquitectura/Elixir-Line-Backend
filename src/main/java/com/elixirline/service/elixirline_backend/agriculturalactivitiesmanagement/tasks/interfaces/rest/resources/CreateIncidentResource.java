package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record CreateIncidentResource(
        Long taskId,
        String description,
        MultipartFile imageFile
) {
}
