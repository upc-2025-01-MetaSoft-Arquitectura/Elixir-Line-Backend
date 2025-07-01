package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BottlingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage.BottlingStageResource;

public class BottlingStageResourceAssembler {
    public static BottlingStageResource toResource(BottlingStage bottlingStage) {
        return new BottlingStageResource(
                bottlingStage.getId(),
                bottlingStage.getBatchId(),
                bottlingStage.getEmployee(),
                bottlingStage.getStartDate(),
                bottlingStage.getEndDate(),
                bottlingStage.getBottlingLine(),
                bottlingStage.getFilledBottles(),
                bottlingStage.getBottleVolume(),
                bottlingStage.getTotalVolume(),
                bottlingStage.getSealingType(),
                bottlingStage.getVineyardCode(),
                bottlingStage.getTemperature(),
                bottlingStage.getFilteredBeforeBottling(),
                bottlingStage.getLabelsAtThisStage(),
                bottlingStage.getCapsuleOrSealApplication(),
                bottlingStage.getComment(),
                bottlingStage.getCompletionStatus(),
                bottlingStage.getCurrentStage(),
                bottlingStage.getCompletedAt(),
                bottlingStage.getDataHash()
        );
    }
}
