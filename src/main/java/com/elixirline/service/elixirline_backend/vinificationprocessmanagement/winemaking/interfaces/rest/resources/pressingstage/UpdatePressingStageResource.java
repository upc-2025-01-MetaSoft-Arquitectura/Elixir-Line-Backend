package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;

import java.time.LocalDate;

public record UpdatePressingStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        String pressType,
        Double pressure,
        Integer duration,
        Double pomadeWeight,
        Double yield,
        String mustUsage,
        String comment,
        CompletionStatus completionStatus
) { }
