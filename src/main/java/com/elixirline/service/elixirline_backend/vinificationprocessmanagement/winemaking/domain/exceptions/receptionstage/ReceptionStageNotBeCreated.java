package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.receptionstage;

public class ReceptionStageNotBeCreated extends RuntimeException {
    public ReceptionStageNotBeCreated(String message) {
        super("The reception stage could not be created: " + message);
    }
}
