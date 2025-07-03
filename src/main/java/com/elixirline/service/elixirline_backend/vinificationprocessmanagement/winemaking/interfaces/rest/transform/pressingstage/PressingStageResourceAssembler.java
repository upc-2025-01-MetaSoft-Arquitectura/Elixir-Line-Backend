package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.PressingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.PressingStageResource;

public class PressingStageResourceAssembler {
    public static PressingStageResource toResource(PressingStage pressingStage) {
        return new PressingStageResource(
                pressingStage.getId(),
                pressingStage.getBatchId(),
                pressingStage.getEmployee(),
                pressingStage.getStartDate(),
                pressingStage.getEndDate(),
                pressingStage.getPressType(),
                pressingStage.getPressure(),
                pressingStage.getDuration(),
                pressingStage.getPomadeWeight(),
                pressingStage.getYield(),
                pressingStage.getMustUsage(),
                pressingStage.getComment(),
                pressingStage.getCompletionStatus(),
                pressingStage.getCurrentStage(),
                pressingStage.getCompletedAt(),
                pressingStage.getDataHash()
        );
    }
}
