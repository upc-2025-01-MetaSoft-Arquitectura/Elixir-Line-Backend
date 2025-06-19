package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage.*;

import java.util.Optional;


public record UpdateFiltrationStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<FilterType> filterType,
        Optional<FilterMedium> filterMedium,
        Optional<Porosity> porosity,
        Optional<FiltrationTurbidity> initialTurbidity,
        Optional<FiltrationTurbidity> finalTurbidity,
        Optional<FiltrationTemperature> temperature,
        Optional<Pressure> pressure,
        Optional<FiltrationVolume> filteredVolume,
        Optional<Boolean> sterileFiltration,
        Optional<Boolean> changedFiltration,
        Optional<ChangeReason> changeReason,
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdateFiltrationStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        filterType = Optional.ofNullable(filterType).orElse(Optional.empty());
        filterMedium = Optional.ofNullable(filterMedium).orElse(Optional.empty());
        porosity = Optional.ofNullable(porosity).orElse(Optional.empty());
        initialTurbidity = Optional.ofNullable(initialTurbidity).orElse(Optional.empty());
        finalTurbidity = Optional.ofNullable(finalTurbidity).orElse(Optional.empty());
        temperature = Optional.ofNullable(temperature).orElse(Optional.empty());
        pressure = Optional.ofNullable(pressure).orElse(Optional.empty());
        filteredVolume = Optional.ofNullable(filteredVolume).orElse(Optional.empty());
        sterileFiltration = Optional.ofNullable(sterileFiltration).orElse(Optional.empty());
        changedFiltration = Optional.ofNullable(changedFiltration).orElse(Optional.empty());
        changeReason = Optional.ofNullable(changeReason).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }

}
