package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;

import java.time.LocalDate;

public record UpdateReceptionStageResource(
        String employee,
        LocalDate startDate,
        LocalDate endDate,
        Double sugarLevel,
        Double pHLevel,
        Double temperature,
        Double quantityKg,
        String comment,
        CompletionStatus completionStatus
) { }
