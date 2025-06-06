package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class VinegrowerNotBeCreated extends RuntimeException {
    public VinegrowerNotBeCreated(String message) {
        super("The vinegrower could not be created: " +message);
    }
}
