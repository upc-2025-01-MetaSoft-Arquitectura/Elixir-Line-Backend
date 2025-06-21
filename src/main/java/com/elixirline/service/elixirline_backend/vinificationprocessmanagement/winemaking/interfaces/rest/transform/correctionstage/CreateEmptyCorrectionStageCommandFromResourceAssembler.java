package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateEmptyCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage.CreateEmptyCorrectionStageResource;

public class CreateEmptyCorrectionStageCommandFromResourceAssembler {
    public static CreateEmptyCorrectionStageCommand toCommandFromResource(CreateEmptyCorrectionStageResource resource, Long batchId) {
        return new CreateEmptyCorrectionStageCommand(
                batchId
        );
    }
}
