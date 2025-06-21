package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessAgingStageResource extends ProcessStageResource{
    private ContainerType containerType;
    private Material material;
    private ContainerCode containerCode;
    private AverageTemperature averageTemperature;
    private AgingVolume volume;
    private AgingDuration duration;
    private Frequency frequency;
    private Batonnage batonnage;
    private Refills refills;
    private Racking rackings;
    private Purpose purpose;
    private CurrentStage currentStage;
}
