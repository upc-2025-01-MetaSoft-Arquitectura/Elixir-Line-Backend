package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;

public record CreateBottlingStageCommand(
        Long batchId,
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
        Boolean filteredBeforeBottling, // Filtrado antes del embotellado
        Boolean labelsAtThisStage, // Etiquetas en esta etapa
        Boolean capsuleOrSealApplication, // Aplicación de cápsulas o precintos
        Comment comment
) { }
