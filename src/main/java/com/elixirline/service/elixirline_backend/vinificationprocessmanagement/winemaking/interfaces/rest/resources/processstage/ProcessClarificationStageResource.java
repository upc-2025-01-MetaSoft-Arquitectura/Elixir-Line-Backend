package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ProcessClarificationStageResource extends ProcessStageResource {
    private ClarificationMethod methodUsed;
    private ClarificationTurbidity initialTurbidity;
    private ClarificationTurbidity finalTurbidity;
    private ClarificationVolume volume;
    private ClarificationTemperature temperature;
    private DurationClarification duration;
    private Map<String, Double> clarifyingAgents;
    private CurrentStage currentStage;
}
