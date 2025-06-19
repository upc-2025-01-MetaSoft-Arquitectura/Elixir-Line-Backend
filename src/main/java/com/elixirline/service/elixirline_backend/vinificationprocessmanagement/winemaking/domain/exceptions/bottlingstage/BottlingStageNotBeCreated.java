package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.bottlingstage;

public class BottlingStageNotBeCreated extends RuntimeException {
    public BottlingStageNotBeCreated(String message) {
        super("The bottling stage could not be created: " + message);
    }
}
