package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage.*;

public record PressingStageResource(
        Long pressingStageId,
        Long batchId,
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        PressType pressType,
        PressPressure pressure,
        PressingDuration duration,
        PomadeWeight pomadeWeight,
        Yield yield,
        MustUsage mustUsage,
        Comment comment,
        CompletionStatus completionStatus,
        CurrentStage currentStage,
        java.time.LocalDateTime completedAt
) { }