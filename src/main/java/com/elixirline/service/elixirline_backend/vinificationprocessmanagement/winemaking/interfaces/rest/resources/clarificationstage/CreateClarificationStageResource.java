package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import java.util.Map;

public record CreateClarificationStageResource(
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        ClarificationMethod methodUsed,
        ClarificationTurbidity initialTurbidity,
        ClarificationTurbidity finalTurbidity,
        ClarificationVolume volume,
        ClarificationTemperature temperature,
        DurationClarification duration,
        Map<String, Double> clarifyingAgents,
        Comment comment
) { }
