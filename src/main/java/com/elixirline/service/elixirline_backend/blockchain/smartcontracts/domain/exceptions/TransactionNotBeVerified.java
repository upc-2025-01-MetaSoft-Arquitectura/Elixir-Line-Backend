package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

public class TransactionNotBeVerified extends RuntimeException {
    public TransactionNotBeVerified(String message) {
        super("The transaction could not be verified: " + message);
    }
}
