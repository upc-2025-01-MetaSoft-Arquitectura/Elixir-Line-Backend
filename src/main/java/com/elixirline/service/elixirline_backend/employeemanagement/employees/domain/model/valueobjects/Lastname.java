package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

public record Lastname(String lastname) {
    public Lastname {
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        }
    }
}
