package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.agingstage;

public class AgingStageNotFoundException extends RuntimeException {
    public AgingStageNotFoundException(Long batchId) {
        super("Could not find aging stage for batch " + batchId);
    }
}
