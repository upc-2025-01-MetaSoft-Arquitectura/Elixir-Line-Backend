package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.receptionstage;

public class ReceptionStageNotBeUpdated extends RuntimeException {
    public ReceptionStageNotBeUpdated(String message) {
        super("The reception stage could not be updated: " + message);
    }
}
