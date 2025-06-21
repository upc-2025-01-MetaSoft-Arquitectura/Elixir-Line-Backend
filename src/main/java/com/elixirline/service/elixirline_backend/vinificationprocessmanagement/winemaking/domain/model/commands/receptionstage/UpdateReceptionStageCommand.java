package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage.*;

import java.util.Optional;

public record UpdateReceptionStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<ReceptionSugarLevel> sugarLevel,
        Optional<ReceptionPHLevel> pHLevel,
        Optional<ReceptionTemperature> temperature,
        Optional<QuantityKg> quantityKg,
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdateReceptionStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        sugarLevel = Optional.ofNullable(sugarLevel).orElse(Optional.empty());
        pHLevel = Optional.ofNullable(pHLevel).orElse(Optional.empty());
        temperature = Optional.ofNullable(temperature).orElse(Optional.empty());
        quantityKg = Optional.ofNullable(quantityKg).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }

}
