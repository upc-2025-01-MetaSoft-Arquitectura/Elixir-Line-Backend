package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record MustUsage(
        @Column(name = "must_usage_value")
        String value
) {
    public MustUsage {
        if (value != null && value.length() > 100) {
            throw new IllegalArgumentException("Must usage cannot be longer than 100 characters.");
        }
    }

    @JsonValue
    public String getMustUsage() {
        return value;
    }

    @JsonCreator
    public static MustUsage from(String mustUsage) {
        return new MustUsage(mustUsage);
    }
}
