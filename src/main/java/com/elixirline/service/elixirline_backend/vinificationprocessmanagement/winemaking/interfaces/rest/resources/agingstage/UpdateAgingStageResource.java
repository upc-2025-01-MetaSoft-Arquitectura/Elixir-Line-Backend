package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;

import java.time.LocalDate;

public record UpdateAgingStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        String containerType,
        String material,
        String containerCode,
        Double averageTemperature,
        Double volume,
        Integer duration,
        Integer frequency,
        Double batonnage,
        Integer refills,
        Integer rackings,
        String purpose,
        String comment,
        CompletionStatus completionStatus
) { }
