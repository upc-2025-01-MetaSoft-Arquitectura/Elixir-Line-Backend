package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources;

public record SignStageResponseResource(
        Long batchId,
        Long stageId,
        String stageName,
        String transactionHash,
        String status,
        String message,
        String timestamp
) { }
