package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateIncidentCommand;
import org.springframework.web.multipart.MultipartFile;

public class CreateIncidentCommandFromRequestAssembler {
    public static CreateIncidentCommand toCommand(Long taskId, String description, MultipartFile imageFile) {
        return new CreateIncidentCommand(taskId, description, imageFile);
    }
}
