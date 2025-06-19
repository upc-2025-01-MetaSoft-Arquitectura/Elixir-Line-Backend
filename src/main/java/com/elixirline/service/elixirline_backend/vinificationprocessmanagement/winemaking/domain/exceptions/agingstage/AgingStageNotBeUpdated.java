package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.agingstage;

public class AgingStageNotBeUpdated extends RuntimeException {
    public AgingStageNotBeUpdated(String message) {
        super("The aging stage could not be updated: " + message);
    }
}
