package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage.*;
import java.util.Optional;

public record UpdatePressingStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<PressType> pressType,
        Optional<PressPressure> pressure,
        Optional<PressingDuration> duration,
        Optional<PomadeWeight> pomadeWeight,
        Optional<Yield> yield,
        Optional<MustUsage> mustUsage,
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdatePressingStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        pressType = Optional.ofNullable(pressType).orElse(Optional.empty());
        pressure = Optional.ofNullable(pressure).orElse(Optional.empty());
        duration = Optional.ofNullable(duration).orElse(Optional.empty());
        pomadeWeight = Optional.ofNullable(pomadeWeight).orElse(Optional.empty());
        yield = Optional.ofNullable(yield).orElse(Optional.empty());
        mustUsage = Optional.ofNullable(mustUsage).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }

}
