package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.UpdateReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.receptionstage.*;

import java.util.Optional;

public class UpdateReceptionStageCommandFromResourceAssembler {
    public static UpdateReceptionStageCommand toCommandFromResource(Long batchId, UpdateReceptionStageResource resource) {
        return new UpdateReceptionStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.sugarLevel() != null ? Optional.of(new ReceptionSugarLevel(resource.sugarLevel())) : Optional.empty(),
                resource.pHLevel() != null ? Optional.of(new ReceptionPHLevel(resource.pHLevel())) : Optional.empty(),
                resource.temperature() != null ? Optional.of(new ReceptionTemperature(resource.temperature())) : Optional.empty(),
                resource.quantityKg() != null ? Optional.of(new QuantityKg(resource.quantityKg())) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}
