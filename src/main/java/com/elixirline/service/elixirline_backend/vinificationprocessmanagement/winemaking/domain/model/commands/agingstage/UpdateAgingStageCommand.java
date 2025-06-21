package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;

import java.util.Optional;


public record UpdateAgingStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<ContainerType> containerType,
        Optional<Material> material,
        Optional<ContainerCode> containerCode,
        Optional<AverageTemperature> averageTemperature,
        Optional<AgingVolume> volume,
        Optional<AgingDuration> duration,
        Optional<Frequency> frequency,
        Optional<Batonnage> batonnage,
        Optional<Refills> refills,
        Optional<Racking> rackings,
        Optional<Purpose> purpose,
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdateAgingStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        containerType = Optional.ofNullable(containerType).orElse(Optional.empty());
        material = Optional.ofNullable(material).orElse(Optional.empty());
        containerCode = Optional.ofNullable(containerCode).orElse(Optional.empty());
        averageTemperature = Optional.ofNullable(averageTemperature).orElse(Optional.empty());
        volume = Optional.ofNullable(volume).orElse(Optional.empty());
        duration = Optional.ofNullable(duration).orElse(Optional.empty());
        frequency = Optional.ofNullable(frequency).orElse(Optional.empty());
        batonnage = Optional.ofNullable(batonnage).orElse(Optional.empty());
        refills = Optional.ofNullable(refills).orElse(Optional.empty());
        rackings = Optional.ofNullable(rackings).orElse(Optional.empty());
        purpose = Optional.ofNullable(purpose).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }
}
