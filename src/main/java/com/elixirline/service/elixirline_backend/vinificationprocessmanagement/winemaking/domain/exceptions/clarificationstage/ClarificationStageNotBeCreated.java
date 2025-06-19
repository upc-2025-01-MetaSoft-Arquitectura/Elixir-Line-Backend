package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.clarificationstage;

public class ClarificationStageNotBeCreated extends RuntimeException {
    public ClarificationStageNotBeCreated(String message) {
        super("The clarification stage could not be created: " + message);
    }
}
