package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessBottlingStageResource extends ProcessStageResource {
    private BottlingLine bottlingLine;
    private FilledBottles filledBottles;
    private BottleVolume bottleVolume;
    private TotalVolume totalVolume;
    private SealingType sealingType;
    private VineyardCodeBottling vineyardCode;
    private BottlingTemperature temperature;
    private Boolean filteredBeforeBottling;
    private Boolean labelsAtThisStage;
    private Boolean capsuleOrSealApplication;
    private CurrentStage currentStage;
}
