package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Name(String name) {
    public Name {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static Name from(String name) {
        return new Name(name);
    }
}
