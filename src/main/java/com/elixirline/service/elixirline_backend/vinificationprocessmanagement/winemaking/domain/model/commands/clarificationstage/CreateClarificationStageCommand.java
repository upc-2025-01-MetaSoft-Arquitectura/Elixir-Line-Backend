package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import java.util.Map;

public record CreateClarificationStageCommand(
        Long batchId,
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        ClarificationMethod methodUsed,
        ClarificationTurbidity initialTurbidity,
        ClarificationTurbidity finalTurbidity,
        ClarificationVolume volume,
        ClarificationTemperature temperature,
        DurationClarification duration,
        Map<String, Double> clarifyingAgents, // Agentes clarificantes y sus dosis
        Comment comment
) { }
