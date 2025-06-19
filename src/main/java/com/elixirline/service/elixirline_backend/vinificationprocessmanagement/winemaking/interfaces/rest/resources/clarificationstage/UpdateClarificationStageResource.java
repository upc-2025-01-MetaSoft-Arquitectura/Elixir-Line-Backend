package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import java.time.LocalDate;
import java.util.Map;

public record UpdateClarificationStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        String methodUsed,
        Double initialTurbidity,
        Double finalTurbidity,
        Double volume,
        Double temperature,
        Integer duration,
        Map<String, Double> clarifyingAgents,
        String comment,
        CompletionStatus completionStatus
) { }
