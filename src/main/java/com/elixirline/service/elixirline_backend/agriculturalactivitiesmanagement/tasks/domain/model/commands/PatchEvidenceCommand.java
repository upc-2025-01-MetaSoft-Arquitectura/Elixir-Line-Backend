package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands;

import org.springframework.web.multipart.MultipartFile;

public record PatchEvidenceCommand(
        Long evidenceId,
        Long taskId,
        String description,
        Integer progressPercentage,
        MultipartFile imageFile
) {
}
