package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class WinegrowerNotBeUpdated extends RuntimeException {
    public WinegrowerNotBeUpdated(String message) {
        super("The vinegrower could not be updated: " + message);
    }
}
