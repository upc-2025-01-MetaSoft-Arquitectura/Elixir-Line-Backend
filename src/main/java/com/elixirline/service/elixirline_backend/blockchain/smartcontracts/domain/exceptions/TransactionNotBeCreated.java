package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

public class TransactionNotBeCreated extends RuntimeException {
    public TransactionNotBeCreated(String message) {
        super("The transaction could not be created: " + message);
    }
}
