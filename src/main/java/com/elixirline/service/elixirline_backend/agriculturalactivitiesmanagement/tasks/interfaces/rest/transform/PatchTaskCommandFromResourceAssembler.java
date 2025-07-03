package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.PatchTaskResource;

public class PatchTaskCommandFromResourceAssembler {
    public static PatchTaskCommand toCommandFromResource(Long taskId, PatchTaskResource resource) {
        return new PatchTaskCommand(
                taskId,
                resource.title(),
                resource.description(),
                resource.startDate(),
                resource.endDate(),
                resource.winegrowerId(),
                resource.batchId(),
                resource.suppliesIds(),
                resource.type()
        );
    }
}
