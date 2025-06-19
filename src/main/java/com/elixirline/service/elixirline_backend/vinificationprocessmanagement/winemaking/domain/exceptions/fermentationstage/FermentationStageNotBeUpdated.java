package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.fermentationstage;

public class FermentationStageNotBeUpdated extends RuntimeException {
    public FermentationStageNotBeUpdated(String message) {
        super("The fermentation stage could not be updated: " + message);
    }
}
