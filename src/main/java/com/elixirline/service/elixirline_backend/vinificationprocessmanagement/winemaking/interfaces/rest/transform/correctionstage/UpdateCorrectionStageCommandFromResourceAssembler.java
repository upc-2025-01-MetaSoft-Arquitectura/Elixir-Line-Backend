package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.UpdateCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.correctionstage.UpdateCorrectionStageResource;

import java.util.Optional;

public class UpdateCorrectionStageCommandFromResourceAssembler {
    public static UpdateCorrectionStageCommand toCommandFromResource(Long batchId, UpdateCorrectionStageResource resource) {
        return new UpdateCorrectionStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.initialSugarLevel() != null ? Optional.of(new CorrectionSugarLevel(resource.initialSugarLevel())) : Optional.empty(),
                resource.finalSugarLevel() != null ? Optional.of(new CorrectionSugarLevel(resource.finalSugarLevel())) : Optional.empty(),
                resource.addedSugar() != null ? Optional.of(new QuantitySugarKg(resource.addedSugar())) : Optional.empty(),
                resource.initialPH() != null ? Optional.of(new CorrectionPHLevel(resource.initialPH())) : Optional.empty(),
                resource.finalPH() != null ? Optional.of(new CorrectionPHLevel(resource.finalPH())) : Optional.empty(),
                resource.acidType() != null ? Optional.of(new AcidType(resource.acidType())) : Optional.empty(),
                resource.addedAcid() != null ? Optional.of(new AddedAcid(resource.addedAcid())) : Optional.empty(),
                resource.addedSulphites() != null ? Optional.of(new AddedSulphites(resource.addedSulphites())) : Optional.empty(),
                resource.nutrients() != null ? Optional.of(resource.nutrients()) : Optional.empty(),
                resource.justification() != null ? Optional.of(new Justification(resource.justification())) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}
