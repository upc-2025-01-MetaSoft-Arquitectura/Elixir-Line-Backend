package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.CreateAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.agingstage.CreateAgingStageResource;

public class CreateAgingStageCommandFromResourceAssembler {
    public static CreateAgingStageCommand toCommandFromResource(CreateAgingStageResource resource, Long batchId) {
        return new CreateAgingStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.containerType(),
                resource.material(),
                resource.containerCode(),
                resource.averageTemperature(),
                resource.volume(),
                resource.duration(),
                resource.frequency(),
                resource.batonnage(),
                resource.refills(),
                resource.rackings(),
                resource.purpose(),
                resource.comment()
        );
    }
}
