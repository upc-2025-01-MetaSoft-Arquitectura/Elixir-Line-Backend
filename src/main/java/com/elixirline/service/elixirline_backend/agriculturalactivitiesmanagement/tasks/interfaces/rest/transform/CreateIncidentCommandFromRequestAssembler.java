package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateIncidentCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.CreateIncidentResource;
import org.springframework.web.multipart.MultipartFile;

public class CreateIncidentCommandFromRequestAssembler {
    public static CreateIncidentCommand toCommand(CreateIncidentResource resource, MultipartFile imageFile) {
        return new CreateIncidentCommand(resource.taskId(), resource.description(), imageFile);
    }
}
