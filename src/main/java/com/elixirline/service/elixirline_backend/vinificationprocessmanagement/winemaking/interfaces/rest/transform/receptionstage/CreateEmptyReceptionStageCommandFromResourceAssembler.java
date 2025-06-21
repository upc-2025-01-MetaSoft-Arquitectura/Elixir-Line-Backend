package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateEmptyReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.CreateEmptyReceptionStageResource;

public class CreateEmptyReceptionStageCommandFromResourceAssembler {
    public static CreateEmptyReceptionStageCommand toCommandFromResource(CreateEmptyReceptionStageResource resource, Long batchId) {
        return new CreateEmptyReceptionStageCommand(
                batchId
        );
    }
}
