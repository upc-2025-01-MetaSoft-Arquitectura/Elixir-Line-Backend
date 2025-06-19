package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.CreatePressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.CreatePressingStageResource;

public class CreatePressingStageCommandFromResourceAssembler {
    public static CreatePressingStageCommand toCommandFromResource(CreatePressingStageResource resource, Long batchId) {
        return new CreatePressingStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.pressType(),
                resource.pressure(),
                resource.duration(),
                resource.pomadeWeight(),
                resource.yield(),
                resource.mustUsage(),
                resource.comment()
        );
    }
}
