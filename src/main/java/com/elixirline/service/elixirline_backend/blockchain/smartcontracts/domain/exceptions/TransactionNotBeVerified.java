package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

import lombok.Getter;

@Getter
public class TransactionNotBeVerified extends RuntimeException {
    private final String transactionHash;
    private final String reason;

    public TransactionNotBeVerified(String transactionHash, String reason, Throwable cause) {
        super(String.format("Transaction %s could not be verified: %s", transactionHash, reason), cause);
        this.transactionHash = transactionHash;
        this.reason = reason;
    }
}