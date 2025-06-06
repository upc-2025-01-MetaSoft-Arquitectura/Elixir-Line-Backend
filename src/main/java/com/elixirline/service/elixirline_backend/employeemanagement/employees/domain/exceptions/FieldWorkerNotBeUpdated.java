package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class FieldWorkerNotBeUpdated extends RuntimeException {
    public FieldWorkerNotBeUpdated(String message) {
        super("The field worker could not be updated: " + message);
    }
}
