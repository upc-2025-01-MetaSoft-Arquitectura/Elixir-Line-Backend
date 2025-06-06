package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class FieldWorkerNotBeCreated extends RuntimeException {
    public FieldWorkerNotBeCreated(String message) {
        super("The field worker could not be created: " + message);
    }
}
