package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.filtrationstage;

public class FiltrationStageNotBeUpdated extends RuntimeException {
    public FiltrationStageNotBeUpdated(String message) {
        super("The filtration stage could not be updated: " + message);
    }
}
