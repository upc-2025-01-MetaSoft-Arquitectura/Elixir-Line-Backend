package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.CreateEvidenceResource;
import org.springframework.web.multipart.MultipartFile;

public class CreateEvidenceCommandFromRequestAssembler {
    public static CreateEvidenceCommand toCommand(CreateEvidenceResource resource, MultipartFile imageFile) {
        return new CreateEvidenceCommand(
                resource.taskId(),
                resource.description(),
                resource.progressPercentage(),
                imageFile);
    }
}
