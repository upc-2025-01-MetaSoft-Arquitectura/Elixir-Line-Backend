package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage.*;

import java.util.List;

public record CorrectionStageResource(
        Long id,
        Long batchId,
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        CorrectionSugarLevel initialSugarLevel,
        CorrectionSugarLevel finalSugarLevel,
        QuantitySugarKg addedSugar,
        CorrectionPHLevel initialPH,
        CorrectionPHLevel finalPH,
        AcidType acidType,
        AddedAcid addedAcid,
        AddedSulphites addedSulphites,
        List<String> nutrients,
        Justification justification,
        Comment comment,
        CompletionStatus completionStatus,
        CurrentStage currentStage,
        java.time.LocalDateTime completedAt,
        String dataHash
) { }
