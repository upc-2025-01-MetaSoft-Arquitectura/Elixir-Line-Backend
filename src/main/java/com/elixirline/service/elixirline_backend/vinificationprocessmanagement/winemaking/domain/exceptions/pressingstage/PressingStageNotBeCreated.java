package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.pressingstage;

public class PressingStageNotBeCreated extends RuntimeException {
    public PressingStageNotBeCreated(String message) {
        super("The pressing stage could not be created: " + message);
    }
}
