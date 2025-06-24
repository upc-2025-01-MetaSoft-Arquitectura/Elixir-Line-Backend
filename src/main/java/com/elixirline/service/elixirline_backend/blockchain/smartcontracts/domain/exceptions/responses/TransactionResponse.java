package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.responses;

public record TransactionResponse(
        String transactionHash,
        String status,
        String message,
        String timestamp
) {}
