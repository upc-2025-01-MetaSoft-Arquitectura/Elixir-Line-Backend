package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.responses;

public record VerificationResponse(
        boolean isVerified,
        String message
) { }
