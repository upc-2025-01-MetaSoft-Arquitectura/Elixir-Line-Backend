package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.bottlingstage;

public class BottlingStageNotBeUpdated extends RuntimeException {
    public BottlingStageNotBeUpdated(String message) {
        super("The bottling stage could not be updated: " + message);
    }
}
