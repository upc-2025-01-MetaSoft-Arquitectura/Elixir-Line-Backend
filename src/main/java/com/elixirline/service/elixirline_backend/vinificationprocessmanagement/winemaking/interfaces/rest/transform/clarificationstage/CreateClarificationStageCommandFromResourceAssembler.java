package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.CreateClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.CreateClarificationStageResource;

public class CreateClarificationStageCommandFromResourceAssembler {
    public static CreateClarificationStageCommand toCommandFromResource(CreateClarificationStageResource resource, Long batchId) {
        return new CreateClarificationStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.methodUsed(),
                resource.initialTurbidity(),
                resource.finalTurbidity(),
                resource.volume(),
                resource.temperature(),
                resource.duration(),
                resource.clarifyingAgents(),
                resource.comment()
        );
    }
}
