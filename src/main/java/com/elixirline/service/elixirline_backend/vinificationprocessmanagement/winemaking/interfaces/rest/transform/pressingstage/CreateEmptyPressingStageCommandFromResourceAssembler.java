package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.CreateEmptyPressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.CreateEmptyPressingStageResource;

public class CreateEmptyPressingStageCommandFromResourceAssembler {
    public static CreateEmptyPressingStageCommand toCommandFromResource(CreateEmptyPressingStageResource resource, Long batchId) {
        return new CreateEmptyPressingStageCommand(
                batchId
        );
    }
}
