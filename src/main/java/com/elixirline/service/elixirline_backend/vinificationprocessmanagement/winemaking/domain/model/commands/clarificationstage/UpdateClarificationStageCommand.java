package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import java.util.Map;
import java.util.Optional;

public record UpdateClarificationStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<ClarificationMethod> methodUsed,
        Optional<ClarificationTurbidity> initialTurbidity,
        Optional<ClarificationTurbidity> finalTurbidity,
        Optional<ClarificationVolume> volume,
        Optional<ClarificationTemperature> temperature,
        Optional<DurationClarification> duration,
        Optional<Map<String, Double>> clarifyingAgents, // Agentes clarificantes y sus dosis
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdateClarificationStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        methodUsed = Optional.ofNullable(methodUsed).orElse(Optional.empty());
        initialTurbidity = Optional.ofNullable(initialTurbidity).orElse(Optional.empty());
        finalTurbidity = Optional.ofNullable(finalTurbidity).orElse(Optional.empty());
        volume = Optional.ofNullable(volume).orElse(Optional.empty());
        temperature = Optional.ofNullable(temperature).orElse(Optional.empty());
        duration = Optional.ofNullable(duration).orElse(Optional.empty());
        clarifyingAgents = Optional.ofNullable(clarifyingAgents).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }
}
