package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.agingstage;

public class AgingStageNotBeCreated extends RuntimeException {
    public AgingStageNotBeCreated(String message) {
        super("The aging stage could not be created: " + message);
    }
}
