package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.commands;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public record StageDetailsCommand(
        Long batchId,
        Long stageId,
        String stageName,
        LocalDate startDate,
        LocalDate endDate,
        String dataHash,
        String transactionHash,
        String signatureDate,
        boolean completed
) {
    public StageDetailsCommand(
            Long batchId,
            Long stageId,
            String stageName,
            LocalDate startDate,
            LocalDate endDate,
            String dataHash,
            String transactionHash,
            String signatureDate
    ) {
        this(batchId, stageId, stageName, startDate, endDate, dataHash, transactionHash, signatureDate, true);
    }

    // Método factory para crear desde valores de la blockchain
    public static StageDetailsCommand fromBlockchain(
            Long batchId,
            Long stageId,
            String stageName,
            long startDateEpoch,
            long endDateEpoch,
            String dataHash,
            String transactionHash,
            String signatureDate,
            boolean completed
    ) {
        return new StageDetailsCommand(
                batchId,
                stageId,
                stageName,
                Instant.ofEpochSecond(startDateEpoch).atZone(ZoneOffset.UTC).toLocalDate(),
                Instant.ofEpochSecond(endDateEpoch).atZone(ZoneOffset.UTC).toLocalDate(),
                dataHash,
                transactionHash,
                signatureDate,
                completed
        );
    }
}