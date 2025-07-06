package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(String phoneNumber) {
    public PhoneNumber {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
    }

    @JsonValue
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonCreator
    public static PhoneNumber from(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }
}
