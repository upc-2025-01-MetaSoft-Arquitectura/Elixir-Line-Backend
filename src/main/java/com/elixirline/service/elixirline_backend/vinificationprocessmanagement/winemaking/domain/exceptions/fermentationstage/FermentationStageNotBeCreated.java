package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.fermentationstage;

public class FermentationStageNotBeCreated extends RuntimeException {
    public FermentationStageNotBeCreated(String message) {
        super("The fermentation stage could not be created: " + message);
    }
}
