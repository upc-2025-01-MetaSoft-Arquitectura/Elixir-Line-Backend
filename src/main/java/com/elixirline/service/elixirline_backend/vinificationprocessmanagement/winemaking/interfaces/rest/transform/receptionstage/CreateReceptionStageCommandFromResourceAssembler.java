package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.CreateReceptionStageResource;

public class CreateReceptionStageCommandFromResourceAssembler {
    public static CreateReceptionStageCommand toCommandFromResource(CreateReceptionStageResource resource, Long batchId) {
        return new CreateReceptionStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.sugarLevel(),
                resource.pHLevel(),
                resource.temperature(),
                resource.quantityKg(),
                resource.comment()
        );
    }
}
