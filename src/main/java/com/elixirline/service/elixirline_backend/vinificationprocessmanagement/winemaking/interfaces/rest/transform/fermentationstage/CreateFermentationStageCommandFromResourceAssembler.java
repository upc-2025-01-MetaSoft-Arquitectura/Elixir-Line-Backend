package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage.CreateFermentationStageResource;

public class CreateFermentationStageCommandFromResourceAssembler {
    public static CreateFermentationStageCommand toCommandFromResource(CreateFermentationStageResource resource, Long batchId) {
        return new CreateFermentationStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.yeastUsed(),
                resource.fermentationType(),
                resource.initialSugarLevel(),
                resource.finalSugarLevel(),
                resource.initialPH(),
                resource.finalPH(),
                resource.maxTemperature(),
                resource.minTemperature(),
                resource.tankCode(),
                resource.comment()
        );
    }
}
