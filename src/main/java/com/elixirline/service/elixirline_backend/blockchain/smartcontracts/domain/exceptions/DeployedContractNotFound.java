package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

import lombok.Getter;

@Getter
public class DeployedContractNotFound extends RuntimeException {
    private final String operation;
    private final String details;

    public DeployedContractNotFound(String operation, String details, Throwable cause) {
        super(String.format("Deployed contract not found during %s: %s", operation, details), cause);
        this.operation = operation;
        this.details = details;
    }
}
