package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.CorrectionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage.CorrectionStageResource;

public class CorrectionStageResourceAssembler {
    public static CorrectionStageResource toResource(CorrectionStage correctionStage) {
        return new CorrectionStageResource(
                correctionStage.getId(),
                correctionStage.getBatchId(),
                correctionStage.getEmployee(),
                correctionStage.getStartDate(),
                correctionStage.getEndDate(),
                correctionStage.getInitialSugarLevel(),
                correctionStage.getFinalSugarLevel(),
                correctionStage.getAddedSugar(),
                correctionStage.getInitialPH(),
                correctionStage.getFinalPH(),
                correctionStage.getAcidType(),
                correctionStage.getAddedAcid(),
                correctionStage.getAddedSulphites(),
                correctionStage.getNutrients(),
                correctionStage.getJustification(),
                correctionStage.getComment(),
                correctionStage.getCompletionStatus(),
                correctionStage.getCurrentStage(),
                correctionStage.getCompletedAt()
        );
    }
}
