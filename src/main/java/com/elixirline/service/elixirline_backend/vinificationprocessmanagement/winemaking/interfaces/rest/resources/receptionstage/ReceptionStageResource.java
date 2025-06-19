package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage.*;

public record ReceptionStageResource(
        Long receptionStageId,
        Long batchId,
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        ReceptionSugarLevel sugarLevel,
        ReceptionPHLevel pHLevel,
        ReceptionTemperature temperature,
        QuantityKg quantityKg,
        Comment comment,
        CompletionStatus completionStatus,
        CurrentStage currentStage,
        java.time.LocalDateTime completedAt
) { }