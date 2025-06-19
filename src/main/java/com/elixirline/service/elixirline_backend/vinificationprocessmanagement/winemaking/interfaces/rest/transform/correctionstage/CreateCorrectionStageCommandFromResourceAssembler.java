package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.Comment;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage.*;

public class CreateCorrectionStageCommandFromResourceAssembler {
    public static CreateCorrectionStageCommand toCommandFromResource(CreateCorrectionStageResource resource, Long batchId) {
        return new CreateCorrectionStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.initialSugarLevel(),
                resource.finalSugarLevel(),
                resource.addedSugar(),
                resource.initialPH(),
                resource.finalPH(),
                resource.acidType(),
                resource.addedAcid(),
                resource.addedSulphites(),
                resource.nutrients(),
                resource.justification(),
                resource.comment()
        );
    }
}
