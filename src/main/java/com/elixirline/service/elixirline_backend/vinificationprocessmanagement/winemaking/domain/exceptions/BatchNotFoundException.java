package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions;

public class BatchNotFoundException extends RuntimeException {
    public BatchNotFoundException(Long batchId) {
        super("Could not find batch " + batchId);
    }
}