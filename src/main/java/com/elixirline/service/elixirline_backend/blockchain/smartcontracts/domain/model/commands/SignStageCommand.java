package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands;

import java.time.LocalDate;
import java.time.ZoneOffset;

public record SignStageCommand(
     Long batchId,
     Long stageId,
     String stageName,
     LocalDate startDate,
     LocalDate endDate,
     String dataHash
) {
    public long getStartDateEpochSeconds() {
        return startDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
    }

    public long getEndDateEpochSeconds() {
        return endDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
    }
}
