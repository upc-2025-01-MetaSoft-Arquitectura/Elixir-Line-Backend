package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

public record PatchIncidentCommand(Long incidentId,
                                   Long taskId,
                                   String description,
                                   MultipartFile imageFile) {
}
