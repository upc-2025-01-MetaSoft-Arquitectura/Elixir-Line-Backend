package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;

import java.time.LocalDate;
import java.util.List;

public record UpdateCorrectionStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        Double initialSugarLevel,
        Double finalSugarLevel,
        Double addedSugar,
        Double initialPH,
        Double finalPH,
        String acidType,
        Double addedAcid,
        Double addedSulphites,
        List<String> nutrients,
        String justification,
        String comment,
        CompletionStatus completionStatus
) { }
