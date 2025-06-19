package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.pressingstage;

public class PressingStageNotFoundException extends RuntimeException {
    public PressingStageNotFoundException(Long batchId) {
        super("Could not find pressing stage for batch " + batchId);
    }
}
