package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.UpdateClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.clarificationstage.UpdateClarificationStageResource;

import java.util.Optional;

public class UpdateClarificationStageCommandFromResourceAssembler {
    public static UpdateClarificationStageCommand toCommandFromResource(Long batchId, UpdateClarificationStageResource resource) {
        return new UpdateClarificationStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.methodUsed() != null ? Optional.of(new ClarificationMethod(resource.methodUsed())) : Optional.empty(),
                resource.initialTurbidity() != null ? Optional.of(new ClarificationTurbidity(resource.initialTurbidity())) : Optional.empty(),
                resource.finalTurbidity() != null ? Optional.of(new ClarificationTurbidity(resource.finalTurbidity())) : Optional.empty(),
                resource.volume() != null ? Optional.of(new ClarificationVolume(resource.volume())) : Optional.empty(),
                resource.temperature() != null ? Optional.of(new ClarificationTemperature(resource.temperature())) : Optional.empty(),
                resource.duration() != null ? Optional.of(new DurationClarification(resource.duration())) : Optional.empty(),
                resource.clarifyingAgents() != null ? Optional.of(resource.clarifyingAgents()) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}
