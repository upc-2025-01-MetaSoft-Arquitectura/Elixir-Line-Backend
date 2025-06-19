package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.bottlingstage;

public class BottlingStageNotFoundException extends RuntimeException {
    public BottlingStageNotFoundException(Long batchId) {
        super("Could not find clarification stage for batch " + batchId);
    }
}
