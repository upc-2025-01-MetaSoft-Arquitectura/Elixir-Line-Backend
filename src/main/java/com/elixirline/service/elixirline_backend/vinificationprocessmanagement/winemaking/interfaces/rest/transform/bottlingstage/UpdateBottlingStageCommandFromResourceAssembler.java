package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.bottlingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.UpdateBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.bottlingstage.UpdateBottlingStageResource;

import java.util.Optional;

public class UpdateBottlingStageCommandFromResourceAssembler {
    public static UpdateBottlingStageCommand toCommandFromResource(Long batchId, UpdateBottlingStageResource resource) {
        return new UpdateBottlingStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.bottlingLine() != null ? Optional.of(new BottlingLine(resource.bottlingLine())) : Optional.empty(),
                resource.filledBottles() != null ? Optional.of(new FilledBottles(resource.filledBottles())) : Optional.empty(),
                resource.bottleVolume() != null ? Optional.of(new BottleVolume(resource.bottleVolume())) : Optional.empty(),
                resource.totalVolume() != null ? Optional.of(new TotalVolume(resource.totalVolume())) : Optional.empty(),
                resource.sealingType() != null ? Optional.of(new SealingType(resource.sealingType())) : Optional.empty(),
                resource.vineyardCode() != null ? Optional.of(new VineyardCodeBottling(resource.vineyardCode())) : Optional.empty(),
                resource.temperature() != null ? Optional.of(new BottlingTemperature(resource.temperature())) : Optional.empty(),
                resource.filteredBeforeBottling() != null ? Optional.of(resource.filteredBeforeBottling()) : Optional.empty(),
                resource.labelsAtThisStage() != null ? Optional.of(resource.labelsAtThisStage()) : Optional.empty(),
                resource.capsuleOrSealApplication() != null ? Optional.of(resource.capsuleOrSealApplication()) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}
