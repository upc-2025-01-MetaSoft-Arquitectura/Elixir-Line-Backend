package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ChangeReason(String changeReason) {
    public ChangeReason {
        if (changeReason != null && changeReason.length() > 255) {
            throw new IllegalArgumentException("Change reason cannot be longer than 255 characters.");
        }
    }

    @JsonValue
    public String getReason() {
        return changeReason;
    }
}
