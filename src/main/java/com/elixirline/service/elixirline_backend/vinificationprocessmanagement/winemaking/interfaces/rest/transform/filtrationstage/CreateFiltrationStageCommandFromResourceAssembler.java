package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.CreateFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage.CreateFiltrationStageResource;

public class CreateFiltrationStageCommandFromResourceAssembler {
    public static CreateFiltrationStageCommand toCommandFromResource(CreateFiltrationStageResource resource, Long batchId) {
        return new CreateFiltrationStageCommand(
                batchId,
                resource.employee(),
                resource.startDate(),
                resource.endDate(),
                resource.filterType(),
                resource.filterMedium(),
                resource.porosity(),
                resource.initialTurbidity(),
                resource.finalTurbidity(),
                resource.temperature(),
                resource.pressure(),
                resource.filteredVolume(),
                resource.sterileFiltration(),
                resource.changedFiltration(),
                resource.changeReason(),
                resource.comment()
        );
    }
}