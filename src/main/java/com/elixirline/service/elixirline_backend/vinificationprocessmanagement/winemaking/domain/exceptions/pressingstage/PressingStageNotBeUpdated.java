package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.pressingstage;

public class PressingStageNotBeUpdated extends RuntimeException {
    public PressingStageNotBeUpdated(String message) {
        super("The pressing stage could not be updated: " + message);
    }
}
