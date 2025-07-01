package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage.FiltrationStageResource;

public class FiltrationStageResourceAssembler {
    public static FiltrationStageResource toResource(FiltrationStage filtrationStage) {
        return new FiltrationStageResource(
                filtrationStage.getId(),
                filtrationStage.getBatchId(),
                filtrationStage.getEmployee(),
                filtrationStage.getStartDate(),
                filtrationStage.getEndDate(),
                filtrationStage.getFilterType(),
                filtrationStage.getFilterMedium(),
                filtrationStage.getPorosity(),
                filtrationStage.getInitialTurbidity(),
                filtrationStage.getFinalTurbidity(),
                filtrationStage.getTemperature(),
                filtrationStage.getPressure(),
                filtrationStage.getFilteredVolume(),
                filtrationStage.getSterileFiltration(),
                filtrationStage.getChangedFiltration(),
                filtrationStage.getChangeReason(),
                filtrationStage.getComment(),
                filtrationStage.getCompletionStatus(),
                filtrationStage.getCurrentStage(),
                filtrationStage.getCompletedAt(),
                filtrationStage.getDataHash()
        );
    }
}