package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage.*;

public record CreateFermentationStageCommand(
        Long batchId,
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        Yeast yeastUsed,
        TypeFermentation fermentationType,
        FermentationSugarLevel initialSugarLevel,
        FermentationSugarLevel finalSugarLevel,
        FermentationPHLevel initialPH,
        FermentationPHLevel finalPH,
        FermentationTemperature maxTemperature,
        FermentationTemperature minTemperature,
        TankCode tankCode,
        Comment comment
) { }
