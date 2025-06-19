package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;

public record AgingStageResource(
        Long agingStageId,
        Long batchId,
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        ContainerType containerType,
        Material material,
        ContainerCode containerCode,
        AverageTemperature averageTemperature,
        AgingVolume volume,
        AgingDuration duration,
        Frequency frequency,
        Batonnage batonnage,
        Refills refills,
        Racking rackings,
        Purpose purpose,
        Comment comment,
        CompletionStatus completionStatus,
        CurrentStage currentStage,
        java.time.LocalDateTime completedAt
) { }