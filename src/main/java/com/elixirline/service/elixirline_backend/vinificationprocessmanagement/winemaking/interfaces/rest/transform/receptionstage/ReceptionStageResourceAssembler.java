package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ReceptionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.ReceptionStageResource;

public class ReceptionStageResourceAssembler {
    public static ReceptionStageResource toResource(ReceptionStage receptionStage) {
        return new ReceptionStageResource(
                receptionStage.getId(),
                receptionStage.getBatchId(),
                receptionStage.getEmployee(),
                receptionStage.getStartDate(),
                receptionStage.getEndDate(),
                receptionStage.getSugarLevel(),
                receptionStage.getPHLevel(),
                receptionStage.getTemperature(),
                receptionStage.getQuantityKg(),
                receptionStage.getComment(),
                receptionStage.getCompletionStatus(),
                receptionStage.getCurrentStage(),
                receptionStage.getCompletedAt()
        );
    }
}
