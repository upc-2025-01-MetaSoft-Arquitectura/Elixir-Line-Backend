package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.UpdateTasksResource;

//UpdateTaskCommand
public class UpdateTaskCommandFromResourceAssembler {
    public static UpdateTaskCommand toCommandFromResource(Long taskId, UpdateTasksResource resource) {
        return new UpdateTaskCommand(
                taskId,
                resource.title(),
                resource.description(),
                resource.startDate(),
                resource.endDate(),
                resource.winegrowerId(),
                resource.fieldWorkerName(),
                resource.batchId(),
                resource.suppliesIds(),
                resource.type()
        );
    }
}
