package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

public record PhoneNumber(String phoneNumber) {
    public PhoneNumber {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
    }
}
