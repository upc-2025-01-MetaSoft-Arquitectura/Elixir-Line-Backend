package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateEmptyFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage.CreateEmptyFermentationStageResource;

public class CreateEmptyFermentationStageCommandFromResourceAssembler {
    public static CreateEmptyFermentationStageCommand toCommandFromResource(CreateEmptyFermentationStageResource resource, Long batchId) {
        return new CreateEmptyFermentationStageCommand(
                batchId
        );
    }
}
