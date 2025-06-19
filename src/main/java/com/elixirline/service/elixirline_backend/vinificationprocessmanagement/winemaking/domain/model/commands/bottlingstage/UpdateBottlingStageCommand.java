package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;

import java.util.Optional;

public record UpdateBottlingStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<BottlingLine> bottlingLine,
        Optional<FilledBottles> filledBottles,
        Optional<BottleVolume> bottleVolume,
        Optional<TotalVolume> totalVolume,
        Optional<SealingType> sealingType,
        Optional<VineyardCodeBottling> vineyardCode,
        Optional<BottlingTemperature> temperature,
        Optional<Boolean> filteredBeforeBottling,
        Optional<Boolean> labelsAtThisStage,
        Optional<Boolean> capsuleOrSealApplication,
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdateBottlingStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        bottlingLine = Optional.ofNullable(bottlingLine).orElse(Optional.empty());
        filledBottles = Optional.ofNullable(filledBottles).orElse(Optional.empty());
        bottleVolume = Optional.ofNullable(bottleVolume).orElse(Optional.empty());
        totalVolume = Optional.ofNullable(totalVolume).orElse(Optional.empty());
        sealingType = Optional.ofNullable(sealingType).orElse(Optional.empty());
        vineyardCode = Optional.ofNullable(vineyardCode).orElse(Optional.empty());
        temperature = Optional.ofNullable(temperature).orElse(Optional.empty());
        filteredBeforeBottling = Optional.ofNullable(filteredBeforeBottling).orElse(Optional.empty());
        labelsAtThisStage = Optional.ofNullable(labelsAtThisStage).orElse(Optional.empty());
        capsuleOrSealApplication = Optional.ofNullable(capsuleOrSealApplication).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }
}
