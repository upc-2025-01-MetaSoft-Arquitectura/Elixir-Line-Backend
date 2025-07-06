package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ClarificationMethod(String clarificationMethod) {
    public ClarificationMethod {
        if (clarificationMethod != null && clarificationMethod.length() > 50) {
            throw new IllegalArgumentException("Clarification method cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getClarificationMethod() {
        return clarificationMethod;
    }

    @JsonCreator
    public static ClarificationMethod from(String clarificationMethod) {
        return new ClarificationMethod(clarificationMethod);
    }
}
