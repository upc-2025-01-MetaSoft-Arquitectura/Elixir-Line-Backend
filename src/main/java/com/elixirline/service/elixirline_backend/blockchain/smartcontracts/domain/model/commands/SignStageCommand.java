package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands;

public record SignStageCommand(
     Long batchId,
     Long stageId,
     String stageName,
     long startDate,
     long endDate,
     String dataHash
) { }
