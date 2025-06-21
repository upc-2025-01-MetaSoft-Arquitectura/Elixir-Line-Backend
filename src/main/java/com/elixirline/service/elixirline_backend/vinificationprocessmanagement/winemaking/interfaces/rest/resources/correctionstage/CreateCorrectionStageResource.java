package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage.*;

import java.util.List;

public record CreateCorrectionStageResource(
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
        Comment comment
) { }