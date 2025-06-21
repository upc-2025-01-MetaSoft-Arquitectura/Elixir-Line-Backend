package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage.*;

public record CreateFiltrationStageCommand(
        Long batchId,
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
        Boolean sterileFiltration, // Filtración estéril
        Boolean changedFiltration, // Filtración cambiado
        ChangeReason changeReason, // Motivo del cambio
        Comment comment
) { }
