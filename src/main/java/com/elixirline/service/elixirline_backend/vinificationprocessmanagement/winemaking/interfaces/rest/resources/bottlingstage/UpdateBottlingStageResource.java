package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;

import java.time.LocalDate;

public record UpdateBottlingStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        String bottlingLine,
        Integer filledBottles,
        Double bottleVolume,
        Double totalVolume,
        String sealingType,
        String vineyardCode,
        Double temperature,
        Boolean filteredBeforeBottling,
        Boolean labelsAtThisStage,
        Boolean capsuleOrSealApplication,
        String comment,
        CompletionStatus completionStatus
) { }