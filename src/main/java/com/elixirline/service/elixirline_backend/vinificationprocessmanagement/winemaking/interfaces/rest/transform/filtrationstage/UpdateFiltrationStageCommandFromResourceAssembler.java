package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.UpdateFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.filtrationstage.UpdateFiltrationStageResource;

import java.util.Optional;

public class UpdateFiltrationStageCommandFromResourceAssembler {
    public static UpdateFiltrationStageCommand toCommandFromResource(Long batchId, UpdateFiltrationStageResource resource) {
        return new UpdateFiltrationStageCommand(
                batchId,
                resource.employee() != null ? Optional.of(new Employee(resource.employee())) : Optional.empty(),
                resource.startDate() != null ? Optional.of(new StartDate(resource.startDate())) : Optional.empty(),
                resource.endDate() != null ? Optional.of(new EndDate(resource.endDate())) : Optional.empty(),
                resource.filterType() != null ? Optional.of(new FilterType(resource.filterType())) : Optional.empty(),
                resource.filterMedium() != null ? Optional.of(new FilterMedium(resource.filterMedium())) : Optional.empty(),
                resource.porosity() != null ? Optional.of(new Porosity(resource.porosity())) : Optional.empty(),
                resource.initialTurbidity() != null ? Optional.of(new FiltrationTurbidity(resource.initialTurbidity())) : Optional.empty(),
                resource.finalTurbidity() != null ? Optional.of(new FiltrationTurbidity(resource.finalTurbidity())) : Optional.empty(),
                resource.temperature() != null ? Optional.of(new FiltrationTemperature(resource.temperature())) : Optional.empty(),
                resource.pressure() != null ? Optional.of(new Pressure(resource.pressure())) : Optional.empty(),
                resource.filteredVolume() != null ? Optional.of(new FiltrationVolume(resource.filteredVolume())) : Optional.empty(),
                resource.sterileFiltration() != null ? Optional.of(resource.sterileFiltration()) : Optional.empty(),
                resource.changedFiltration() != null ? Optional.of(resource.changedFiltration()) : Optional.empty(),
                resource.changeReason() != null ? Optional.of(new ChangeReason(resource.changeReason())) : Optional.empty(),
                resource.comment() != null ? Optional.of(new Comment(resource.comment())) : Optional.empty(),
                Optional.ofNullable(resource.completionStatus())
        );
    }
}
