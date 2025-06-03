package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

public record Country(String country) {
    public Country {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null");
        }
    }
}
