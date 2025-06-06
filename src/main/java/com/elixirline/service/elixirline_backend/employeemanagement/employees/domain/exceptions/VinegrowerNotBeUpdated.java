package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class VinegrowerNotBeUpdated extends RuntimeException {
    public VinegrowerNotBeUpdated(String message) {
        super("The vinegrower could not be updated: " + message);
    }
}
