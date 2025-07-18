package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage.*;

public record CreateReceptionStageResource(
        Employee employee,
        StartDate startDate,
        EndDate endDate,
        ReceptionSugarLevel sugarLevel,
        ReceptionPHLevel pHLevel,
        ReceptionTemperature temperature,
        QuantityKg quantityKg,
        Comment comment
) { }
