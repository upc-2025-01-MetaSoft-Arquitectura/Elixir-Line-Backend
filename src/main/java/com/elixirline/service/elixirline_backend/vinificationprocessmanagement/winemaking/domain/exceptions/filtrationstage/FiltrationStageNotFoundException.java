package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.filtrationstage;

public class FiltrationStageNotFoundException extends RuntimeException {
    public FiltrationStageNotFoundException(Long batchId) {
        super("Could not find filtration stage for batch " + batchId);
    }
}
