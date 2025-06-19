package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.fermentationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.UpdateFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.fermentationstage.UpdateFermentationStageResource;

import java.util.Optional;

public class UpdateFermentationStageCommandFromResourceAssembler {
    public static UpdateFermentationStageCommand toCommandFromResource(Long batchId, UpdateFermentationStageResource resource) {
        return new UpdateFermentationStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.yeastUsed() != null ? Optional.of(new Yeast(resource.yeastUsed())) : Optional.empty(),
                resource.fermentationType() != null ? Optional.of(new TypeFermentation(resource.fermentationType())) : Optional.empty(),
                resource.initialSugarLevel() != null ? Optional.of(new FermentationSugarLevel(resource.initialSugarLevel())) : Optional.empty(),
                resource.finalSugarLevel() != null ? Optional.of(new FermentationSugarLevel(resource.finalSugarLevel())) : Optional.empty(),
                resource.initialPH() != null ? Optional.of(new FermentationPHLevel(resource.initialPH())) : Optional.empty(),
                resource.finalPH() != null ? Optional.of(new FermentationPHLevel(resource.finalPH())) : Optional.empty(),
                resource.maxTemperature() != null ? Optional.of(new FermentationTemperature(resource.maxTemperature())) : Optional.empty(),
                resource.minTemperature() != null ? Optional.of(new FermentationTemperature(resource.minTemperature())) : Optional.empty(),
                resource.tankCode() != null ? Optional.of(new TankCode(resource.tankCode())) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}
