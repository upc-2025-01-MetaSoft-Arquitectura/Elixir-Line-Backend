package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.UpdatePressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.pressingstage.UpdatePressingStageResource;

import java.util.Optional;

public class UpdatePressingStageCommandFromResourceAssembler {
    public static UpdatePressingStageCommand toCommandFromResource(Long batchId, UpdatePressingStageResource resource) {
        return new UpdatePressingStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.pressType() != null ? Optional.of(new PressType(resource.pressType())) : Optional.empty(),
                resource.pressure() != null ? Optional.of(new PressPressure(resource.pressure())) : Optional.empty(),
                resource.duration() != null ? Optional.of(new PressingDuration(resource.duration())) : Optional.empty(),
                resource.pomadeWeight() != null ? Optional.of(new PomadeWeight(resource.pomadeWeight())) : Optional.empty(),
                resource.yield() != null ? Optional.of(new Yield(resource.yield())) : Optional.empty(),
                resource.mustUsage() != null ? Optional.of(new MustUsage(resource.mustUsage())) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}

