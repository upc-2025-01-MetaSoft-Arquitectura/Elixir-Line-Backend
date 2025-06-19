package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.correctionstage;

public class CorrectionStageNotBeUpdated extends RuntimeException {
    public CorrectionStageNotBeUpdated(String message) {
        super("The correction stage could not be updated: " + message);
    }
}
