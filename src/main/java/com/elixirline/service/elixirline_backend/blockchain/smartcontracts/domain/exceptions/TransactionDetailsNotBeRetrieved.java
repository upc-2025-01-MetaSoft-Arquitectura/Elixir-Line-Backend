package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

public class TransactionDetailsNotBeRetrieved extends RuntimeException {
    public TransactionDetailsNotBeRetrieved(String message) {
        super("The transaction details could not be retrieved: " + message);
    }
}
