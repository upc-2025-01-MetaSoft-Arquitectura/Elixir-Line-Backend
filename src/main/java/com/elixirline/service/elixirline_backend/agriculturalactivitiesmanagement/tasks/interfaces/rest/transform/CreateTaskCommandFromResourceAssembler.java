package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateTasksCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {
    public static CreateTasksCommand toCommandFromResource(CreateTaskResource resource) {
        return new CreateTasksCommand(
                resource.title(),
                resource.description(),
                resource.startDate(),
                resource.endDate(),
                resource.winegrowerId(),
                resource.fieldWorkerName(),
                resource.batchId(),
                resource.suppliesIds()
        );
    }
}
