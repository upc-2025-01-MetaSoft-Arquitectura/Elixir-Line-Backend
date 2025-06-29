package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

import lombok.Getter;

@Getter
public class TransactionDetailsNotBeRetrieved extends RuntimeException {
    private final String transactionHash;
    private final String resource;

    public TransactionDetailsNotBeRetrieved(String transactionHash, String resource, Throwable cause) {
        super(String.format("Could not retrieve details for transaction %s (resource: %s)", transactionHash, resource), cause);
        this.transactionHash = transactionHash;
        this.resource = resource;
    }

}
