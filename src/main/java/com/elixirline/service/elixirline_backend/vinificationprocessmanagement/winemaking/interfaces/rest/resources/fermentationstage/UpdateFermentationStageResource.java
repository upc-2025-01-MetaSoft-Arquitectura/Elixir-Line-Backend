package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;

import java.time.LocalDate;

public record UpdateFermentationStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        Double yeastUsed,
        String fermentationType,
        Double initialSugarLevel,
        Double finalSugarLevel,
        Double initialPH,
        Double finalPH,
        Double maxTemperature,
        Double minTemperature,
        String tankCode,
        String comment,
        CompletionStatus completionStatus
) { }