package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.receptionstage;

public class ReceptionStageNotFoundException extends RuntimeException {
    public ReceptionStageNotFoundException(Long batchId) {
      super("Could not find reception stage for batch " + batchId);
    }
}
