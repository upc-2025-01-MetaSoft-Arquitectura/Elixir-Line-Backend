package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.correctionstage;

public class CorrectionStageNotFoundException extends RuntimeException {
    public CorrectionStageNotFoundException(Long batchId) {
        super("Could not find correction stage for batch " + batchId);
    }
}
