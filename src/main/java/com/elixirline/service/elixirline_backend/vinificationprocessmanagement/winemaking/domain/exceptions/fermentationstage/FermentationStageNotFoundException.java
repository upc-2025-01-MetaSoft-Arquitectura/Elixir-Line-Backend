package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.fermentationstage;

public class FermentationStageNotFoundException extends RuntimeException {
  public FermentationStageNotFoundException(Long batchId) {
    super("Could not find fermentation stage for batch " + batchId);
  }
}
