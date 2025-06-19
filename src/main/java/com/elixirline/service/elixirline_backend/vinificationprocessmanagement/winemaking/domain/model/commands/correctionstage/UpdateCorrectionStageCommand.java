package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage.*;
import java.util.List;
import java.util.Optional;

public record UpdateCorrectionStageCommand(
        Long batchId,
        Optional<Employee> employee,
        Optional<StartDate> startDate,
        Optional<EndDate> endDate,
        Optional<CorrectionSugarLevel> initialSugarLevel,
        Optional<CorrectionSugarLevel> finalSugarLevel,
        Optional<QuantitySugarKg> addedSugar,
        Optional<CorrectionPHLevel> initialPH,
        Optional<CorrectionPHLevel> finalPH,
        Optional<AcidType> acidType,
        Optional<AddedAcid> addedAcid,
        Optional<AddedSulphites> addedSulphites,
        Optional<List<String>> nutrients,
        Optional<Justification> justification,
        Optional<Comment> comment,
        Optional<CompletionStatus> completionStatus
) {
    public UpdateCorrectionStageCommand {
        employee = Optional.ofNullable(employee).orElse(Optional.empty());
        startDate = Optional.ofNullable(startDate).orElse(Optional.empty());
        endDate = Optional.ofNullable(endDate).orElse(Optional.empty());
        initialSugarLevel = Optional.ofNullable(initialSugarLevel).orElse(Optional.empty());
        finalSugarLevel = Optional.ofNullable(finalSugarLevel).orElse(Optional.empty());
        addedSugar = Optional.ofNullable(addedSugar).orElse(Optional.empty());
        initialPH = Optional.ofNullable(initialPH).orElse(Optional.empty());
        finalPH = Optional.ofNullable(finalPH).orElse(Optional.empty());
        acidType = Optional.ofNullable(acidType).orElse(Optional.empty());
        addedAcid = Optional.ofNullable(addedAcid).orElse(Optional.empty());
        addedSulphites = Optional.ofNullable(addedSulphites).orElse(Optional.empty());
        nutrients = Optional.ofNullable(nutrients).orElse(Optional.empty());
        justification = Optional.ofNullable(justification).orElse(Optional.empty());
        comment = Optional.ofNullable(comment).orElse(Optional.empty());
        completionStatus = Optional.ofNullable(completionStatus).orElse(Optional.empty());
    }

}
