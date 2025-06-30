package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

import lombok.Getter;

@Getter
public class BatchIdAndStageNameAlreadyRegistered extends RuntimeException {
    private final String operation;
    private final String details;

    public BatchIdAndStageNameAlreadyRegistered(String operation, String details, Throwable cause) {
        super(String.format("Batch id and stage name already registered during %s: %s", operation, details), cause);
        this.operation = operation;
        this.details = details;
    }
}
