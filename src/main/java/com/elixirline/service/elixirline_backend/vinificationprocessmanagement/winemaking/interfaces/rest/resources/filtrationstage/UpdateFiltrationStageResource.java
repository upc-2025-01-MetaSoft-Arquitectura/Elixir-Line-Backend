package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;

import java.time.LocalDate;

public record UpdateFiltrationStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        String filterType,
        String filterMedium,
        Double porosity,
        Double initialTurbidity,
        Double finalTurbidity,
        Double temperature,
        Double pressure,
        Double filteredVolume,
        Boolean sterileFiltration,
        Boolean changedFiltration,
        String changeReason,
        String comment,
        CompletionStatus completionStatus
) { }
