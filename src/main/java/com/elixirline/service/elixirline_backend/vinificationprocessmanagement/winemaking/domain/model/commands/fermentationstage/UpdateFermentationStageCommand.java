package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage.*;

import java.util.Optional;

public record UpdateFermentationStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<Yeast> yeastUsed,
        Optional<TypeFermentation> fermentationType,
        Optional<FermentationSugarLevel> initialSugarLevel,
        Optional<FermentationSugarLevel> finalSugarLevel,
        Optional<FermentationPHLevel> initialPH,
        Optional<FermentationPHLevel> finalPH,
        Optional<FermentationTemperature> maxTemperature,
        Optional<FermentationTemperature> minTemperature,
        Optional<TankCode> tankCode,
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdateFermentationStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        yeastUsed = Optional.ofNullable(yeastUsed).orElse(Optional.empty());
        fermentationType = Optional.ofNullable(fermentationType).orElse(Optional.empty());
        initialSugarLevel = Optional.ofNullable(initialSugarLevel).orElse(Optional.empty());
        finalSugarLevel = Optional.ofNullable(finalSugarLevel).orElse(Optional.empty());
        initialPH = Optional.ofNullable(initialPH).orElse(Optional.empty());
        finalPH = Optional.ofNullable(finalPH).orElse(Optional.empty());
        maxTemperature = Optional.ofNullable(maxTemperature).orElse(Optional.empty());
        minTemperature = Optional.ofNullable(minTemperature).orElse(Optional.empty());
        tankCode = Optional.ofNullable(tankCode).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }
}
