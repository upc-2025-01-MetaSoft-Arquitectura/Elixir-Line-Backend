package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;


@Embeddable
public record Employee(String employee) {
    public Employee {
        if (employee != null && employee.length() > 120) {
            throw new IllegalArgumentException("Employee cannot be longer than 120 characters.");
        }
    }

    @JsonValue
    public String getEmployee() {
        return employee;
    }
}
