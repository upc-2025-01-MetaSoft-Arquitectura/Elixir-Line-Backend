package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class ProcessCorrectionStageResource extends ProcessStageResource {
    private CorrectionSugarLevel initialSugarLevel;
    private CorrectionSugarLevel finalSugarLevel;
    private QuantitySugarKg addedSugar;
    private CorrectionPHLevel initialPH;
    private CorrectionPHLevel finalPH;
    private AcidType acidType;
    private AddedAcid addedAcid;
    private AddedSulphites addedSulphites;
    private List<String> nutrients;
    private Justification justification;
    private CurrentStage currentStage;
}
