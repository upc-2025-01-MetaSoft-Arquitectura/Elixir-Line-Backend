package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.CreateEmptyBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage.CreateEmptyBottlingStageResource;

public class CreateEmptyBottlingStageCommandFromResourceAssembler {
    public static CreateEmptyBottlingStageCommand toCommandFromResource(CreateEmptyBottlingStageResource resource, Long batchId) {
        return new CreateEmptyBottlingStageCommand(
                batchId
        );
    }
}
