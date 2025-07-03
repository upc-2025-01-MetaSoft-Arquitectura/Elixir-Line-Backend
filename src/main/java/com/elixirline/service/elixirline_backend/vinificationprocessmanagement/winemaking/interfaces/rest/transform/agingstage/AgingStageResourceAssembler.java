package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.AgingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.AgingStageResource;

public class AgingStageResourceAssembler {
    public static AgingStageResource toResource(AgingStage agingStage) {
        return new AgingStageResource(
                agingStage.getId(),
                agingStage.getBatchId(),
                agingStage.getEmployee(),
                agingStage.getStartDate(),
                agingStage.getEndDate(),
                agingStage.getContainerType(),
                agingStage.getMaterial(),
                agingStage.getContainerCode(),
                agingStage.getAverageTemperature(),
                agingStage.getVolume(),
                agingStage.getDuration(),
                agingStage.getFrequency(),
                agingStage.getBatonnage(),
                agingStage.getRefills(),
                agingStage.getRackings(),
                agingStage.getPurpose(),
                agingStage.getComment(),
                agingStage.getCompletionStatus(),
                agingStage.getCurrentStage(),
                agingStage.getCompletedAt(),
                agingStage.getDataHash()
        );
    }
}
