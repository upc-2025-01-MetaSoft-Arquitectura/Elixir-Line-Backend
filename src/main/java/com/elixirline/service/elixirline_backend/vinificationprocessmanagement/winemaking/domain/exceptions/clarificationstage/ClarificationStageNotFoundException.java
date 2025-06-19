package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.clarificationstage;

public class ClarificationStageNotFoundException extends RuntimeException {
    public ClarificationStageNotFoundException(Long batchId) {
        super("Could not find clarification stage for batch " + batchId);
    }
}
