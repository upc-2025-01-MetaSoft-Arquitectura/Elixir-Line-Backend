package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessPressingStageResource extends ProcessStageResource {
    private PressType pressType;
    private PressPressure pressure;
    private PressingDuration duration;
    private PomadeWeight pomadeWeight;
    private Yield yield;
    private MustUsage mustUsage;
    private CurrentStage currentStage;
}
