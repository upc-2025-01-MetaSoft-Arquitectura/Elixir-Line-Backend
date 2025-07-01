package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ClarificationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.ClarificationStageResource;

public class ClarificationStageResourceAssembler {
    public static ClarificationStageResource toResource(ClarificationStage clarificationStage) {
        return new ClarificationStageResource(
                clarificationStage.getId(),
                clarificationStage.getBatchId(),
                clarificationStage.getEmployee(),
                clarificationStage.getStartDate(),
                clarificationStage.getEndDate(),
                clarificationStage.getMethodUsed(),
                clarificationStage.getInitialTurbidity(),
                clarificationStage.getFinalTurbidity(),
                clarificationStage.getVolume(),
                clarificationStage.getTemperature(),
                clarificationStage.getDuration(),
                clarificationStage.getClarifyingAgents(),
                clarificationStage.getComment(),
                clarificationStage.getCompletionStatus(),
                clarificationStage.getCurrentStage(),
                clarificationStage.getCompletedAt(),
                clarificationStage.getDataHash()
        );
    }
}