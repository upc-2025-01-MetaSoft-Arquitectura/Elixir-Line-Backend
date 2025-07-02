package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import org.springframework.web.multipart.MultipartFile;

public class CreateEvidenceCommandFromRequestAssembler {
    public static CreateEvidenceCommand toCommand(Long taskId, String description, Integer progressPercentage, MultipartFile imageFile) {
        return new CreateEvidenceCommand(taskId, description, progressPercentage, imageFile);
    }
}
