package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Country(String country) {
    public Country {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null");
        }
    }

    @JsonValue
    public String getCountry() {
        return country;
    }
}
