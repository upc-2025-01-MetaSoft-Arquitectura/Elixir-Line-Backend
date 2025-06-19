package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch;

public class BatchNotFoundException extends RuntimeException {
    public BatchNotFoundException(Long batchId) {
        super("Could not find batch " + batchId);
    }
}