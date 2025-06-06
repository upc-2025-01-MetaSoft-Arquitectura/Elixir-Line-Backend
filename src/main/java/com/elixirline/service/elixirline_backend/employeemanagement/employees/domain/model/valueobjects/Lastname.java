package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Lastname(String lastname) {
    public Lastname {
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        }
    }

    @JsonValue
    public String getLastname() {
        return lastname;
    }
}
