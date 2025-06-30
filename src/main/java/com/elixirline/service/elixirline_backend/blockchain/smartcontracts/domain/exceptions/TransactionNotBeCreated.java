package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

import lombok.Getter;

@Getter
public class TransactionNotBeCreated extends RuntimeException {
    private final String operation;
    private final String details;

    public TransactionNotBeCreated(String operation, String details, Throwable cause) {
        super(String.format("Transaction failed during %s: %s", operation, details), cause);
        this.operation = operation;
        this.details = details;
    }
}
