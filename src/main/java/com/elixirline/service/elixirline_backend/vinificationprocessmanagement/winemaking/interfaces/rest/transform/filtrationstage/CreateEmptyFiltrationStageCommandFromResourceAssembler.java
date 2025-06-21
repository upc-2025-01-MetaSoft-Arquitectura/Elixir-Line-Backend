package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.CreateEmptyFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage.CreateEmptyFiltrationStageResource;

public class CreateEmptyFiltrationStageCommandFromResourceAssembler {
    public static CreateEmptyFiltrationStageCommand toCommandFromResource(CreateEmptyFiltrationStageResource resource, Long batchId) {
        return new CreateEmptyFiltrationStageCommand(
                batchId
        );
    }
}
