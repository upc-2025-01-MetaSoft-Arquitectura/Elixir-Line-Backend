package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage;


import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.CreateEmptyAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.CreateEmptyAgingStageResource;

public class CreateEmptyAgingStageCommandFromResourceAssembler {
    public static CreateEmptyAgingStageCommand toCommandFromResource(CreateEmptyAgingStageResource resource, Long batchId) {
        return new CreateEmptyAgingStageCommand(
                batchId
        );
    }
}
