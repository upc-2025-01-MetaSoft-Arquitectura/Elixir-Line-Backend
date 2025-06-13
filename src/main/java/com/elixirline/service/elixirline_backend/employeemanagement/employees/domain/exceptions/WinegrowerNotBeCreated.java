package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class WinegrowerNotBeCreated extends RuntimeException {
    public WinegrowerNotBeCreated(String message) {
        super("The vinegrower could not be created: " +message);
    }
}
