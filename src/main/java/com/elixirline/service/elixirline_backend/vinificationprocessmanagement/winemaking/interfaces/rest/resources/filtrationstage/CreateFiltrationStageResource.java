package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage.*;

public record CreateFiltrationStageResource(
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        FilterType filterType,
        FilterMedium filterMedium,
        Porosity porosity,
        FiltrationTurbidity initialTurbidity,
        FiltrationTurbidity finalTurbidity,
        FiltrationTemperature temperature,
        Pressure pressure,
        FiltrationVolume filteredVolume,
        Boolean sterileFiltration,
        Boolean changedFiltration,
        ChangeReason changeReason,
        Comment comment
) { }
