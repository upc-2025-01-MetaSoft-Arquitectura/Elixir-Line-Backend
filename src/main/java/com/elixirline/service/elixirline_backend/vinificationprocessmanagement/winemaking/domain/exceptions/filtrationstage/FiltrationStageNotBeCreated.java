package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.filtrationstage;

public class FiltrationStageNotBeCreated extends RuntimeException {
    public FiltrationStageNotBeCreated(String message) {
        super("The filtration stage could not be created: " + message);
    }
}
