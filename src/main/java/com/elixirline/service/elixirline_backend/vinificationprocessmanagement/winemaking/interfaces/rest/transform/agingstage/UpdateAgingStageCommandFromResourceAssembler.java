package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.UpdateAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.UpdateAgingStageResource;

import java.util.Optional;

public class UpdateAgingStageCommandFromResourceAssembler {
    public static UpdateAgingStageCommand toCommandFromResource(Long batchId, UpdateAgingStageResource resource) {
        return new UpdateAgingStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.containerType() != null ? Optional.of(new ContainerType(resource.containerType())) : Optional.empty(),
                resource.material() != null ? Optional.of(new Material(resource.material())) : Optional.empty(),
                resource.containerCode() != null ? Optional.of(new ContainerCode(resource.containerCode())) : Optional.empty(),
                resource.averageTemperature() != null ? Optional.of(new AverageTemperature(resource.averageTemperature())) : Optional.empty(),
                resource.volume() != null ? Optional.of(new AgingVolume(resource.volume())) : Optional.empty(),
                resource.duration() != null ? Optional.of(new AgingDuration(resource.duration())) : Optional.empty(),
                resource.frequency() != null ? Optional.of(new Frequency(resource.frequency())) : Optional.empty(),
                resource.batonnage() != null ? Optional.of(new Batonnage(resource.batonnage())) : Optional.empty(),
                resource.refills() != null ? Optional.of(new Refills(resource.refills())) : Optional.empty(),
                resource.rackings() != null ? Optional.of(new Racking(resource.rackings())) : Optional.empty(),
                resource.purpose() != null ? Optional.of(new Purpose(resource.purpose())) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}
