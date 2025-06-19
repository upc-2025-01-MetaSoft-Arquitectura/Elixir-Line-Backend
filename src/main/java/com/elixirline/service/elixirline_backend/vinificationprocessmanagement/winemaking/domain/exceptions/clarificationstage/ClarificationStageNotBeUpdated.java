package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.clarificationstage;

public class ClarificationStageNotBeUpdated extends RuntimeException {
    public ClarificationStageNotBeUpdated(String message) {
        super("The clarification stage could not be updated: " + message);
    }
}
