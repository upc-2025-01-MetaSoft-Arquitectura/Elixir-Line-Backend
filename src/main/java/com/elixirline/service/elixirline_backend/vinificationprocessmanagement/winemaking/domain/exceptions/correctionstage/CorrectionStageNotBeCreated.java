package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.correctionstage;

public class CorrectionStageNotBeCreated extends RuntimeException {
    public CorrectionStageNotBeCreated(String message) {
        super("The correction stage could not be created: " + message);
    }
}
