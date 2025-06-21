package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessReceptionStageResource extends ProcessStageResource {
    private ReceptionSugarLevel sugarLevel;
    private ReceptionPHLevel pHLevel;
    private ReceptionTemperature temperature;
    private QuantityKg quantityKg;
    private CurrentStage currentStage;
}
