package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.responses;

public record StageDetailResponse(
        String batchId,
        String stageId,
        String stageName,
        String dataHash,
        boolean isCompleted,
        long startDate,
        long endDate
) { }
