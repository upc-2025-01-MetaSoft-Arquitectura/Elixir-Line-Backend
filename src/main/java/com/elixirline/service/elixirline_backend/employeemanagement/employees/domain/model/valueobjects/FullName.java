package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

public record FullName(String fullname) {
    public FullName {
        if (fullname == null || fullname.isBlank()) {
            throw new IllegalArgumentException("Fullname cannot be null or empty");
        }
    }
}
