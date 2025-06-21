package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch;

public class BatchNotBeCreated extends RuntimeException {
    public BatchNotBeCreated(String message) {
        super("The batch could not be created: " + message);
    }
}
