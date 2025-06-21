package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProcessFermentationStageResource extends ProcessStageResource {
    private Yeast yeastUsed;
    private TypeFermentation fermentationType;
    private FermentationSugarLevel initialSugarLevel;
    private FermentationSugarLevel finalSugarLevel;
    private FermentationPHLevel initialPH;
    private FermentationPHLevel finalPH;
    private FermentationTemperature maxTemperature;
    private FermentationTemperature minTemperature;
    private TankCode tankCode;
    private CurrentStage currentStage;
}
