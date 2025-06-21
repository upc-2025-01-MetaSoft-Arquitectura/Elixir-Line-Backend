package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;

public record CreateBottlingStageResource(
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        BottlingLine bottlingLine,
        FilledBottles filledBottles,
        BottleVolume bottleVolume,
        TotalVolume totalVolume,
        SealingType sealingType,
        VineyardCodeBottling vineyardCode,
        BottlingTemperature temperature,
        Boolean filteredBeforeBottling,
        Boolean labelsAtThisStage,
        Boolean capsuleOrSealApplication,
        Comment comment
) { }
