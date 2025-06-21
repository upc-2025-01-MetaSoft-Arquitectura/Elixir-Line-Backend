package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProcessFiltrationStageResource extends ProcessStageResource {
    private FilterType filterType;
    private FilterMedium filterMedium;
    private Porosity porosity;
    private FiltrationTurbidity initialTurbidity;
    private FiltrationTurbidity finalTurbidity;
    private FiltrationTemperature temperature;
    private Pressure pressure;
    private FiltrationVolume filteredVolume;
    private Boolean sterileFiltration;
    private Boolean changedFiltration;
    private ChangeReason changeReason;
    private CurrentStage currentStage;
}
