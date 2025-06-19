package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage.*;

public record FermentationStageResource(
        Long fermentationStageId,
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
        Comment comment,
        CompletionStatus completionStatus,
        CurrentStage currentStage,
        java.time.LocalDateTime completedAt
) { }
