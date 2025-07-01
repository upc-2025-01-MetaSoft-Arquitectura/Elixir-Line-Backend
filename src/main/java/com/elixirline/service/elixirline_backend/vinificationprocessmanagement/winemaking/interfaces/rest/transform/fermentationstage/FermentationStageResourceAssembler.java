package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FermentationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage.FermentationStageResource;

public class FermentationStageResourceAssembler {
    public static FermentationStageResource toResource(FermentationStage fermentationStage) {
        return new FermentationStageResource(
                fermentationStage.getId(),
                fermentationStage.getBatchId(),
                fermentationStage.getEmployee(),
                fermentationStage.getStartDate(),
                fermentationStage.getEndDate(),
                fermentationStage.getYeastUsed(),
                fermentationStage.getFermentationType(),
                fermentationStage.getInitialSugarLevel(),
                fermentationStage.getFinalSugarLevel(),
                fermentationStage.getInitialPH(),
                fermentationStage.getFinalPH(),
                fermentationStage.getMaxTemperature(),
                fermentationStage.getMinTemperature(),
                fermentationStage.getTankCode(),
                fermentationStage.getComment(),
                fermentationStage.getCompletionStatus(),
                fermentationStage.getCurrentStage(),
                fermentationStage.getCompletedAt(),
                fermentationStage.getDataHash()
        );
    }
}
