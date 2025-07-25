package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch;

public class BatchNotBeUpdated extends RuntimeException {
    public BatchNotBeUpdated(String message) {
        super("The batch could not be updated: " + message);
    }
}
