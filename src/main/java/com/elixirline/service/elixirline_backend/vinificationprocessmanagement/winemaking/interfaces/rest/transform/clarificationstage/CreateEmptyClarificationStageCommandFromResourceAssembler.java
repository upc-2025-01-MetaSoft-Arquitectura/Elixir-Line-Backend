package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.CreateEmptyClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.CreateEmptyClarificationStageResource;

public class CreateEmptyClarificationStageCommandFromResourceAssembler {
    public static CreateEmptyClarificationStageCommand toCommandFromResource(CreateEmptyClarificationStageResource resource, Long batchId) {
        return new CreateEmptyClarificationStageCommand(
                batchId
        );
    }
}
