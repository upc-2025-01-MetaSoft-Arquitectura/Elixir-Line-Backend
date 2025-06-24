package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources;

public record StageDetailsResource(
        String stageName,
        long startDate,
        long endDate,
        String dataHash
) { }
