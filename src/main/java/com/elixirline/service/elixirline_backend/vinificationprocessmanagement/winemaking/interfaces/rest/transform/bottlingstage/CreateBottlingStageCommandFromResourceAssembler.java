package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.CreateBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage.CreateBottlingStageResource;

public class CreateBottlingStageCommandFromResourceAssembler {
    public static CreateBottlingStageCommand toCommandFromResource(CreateBottlingStageResource resource, Long batchId) {
        return new CreateBottlingStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.bottlingLine(),
                resource.filledBottles(),
                resource.bottleVolume(),
                resource.totalVolume(),
                resource.sealingType(),
                resource.vineyardCode(),
                resource.temperature(),
                resource.filteredBeforeBottling(),
                resource.labelsAtThisStage(),
                resource.capsuleOrSealApplication(),
                resource.comment()
        );
    }
}
