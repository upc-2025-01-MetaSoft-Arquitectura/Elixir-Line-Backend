package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Progress(double percentage) {
    public static final Progress ZERO = new Progress(0.0);

    public Progress {
        if (percentage != 0.0 && (percentage < 0 || percentage > 100 || percentage % 12.5 != 0)) {
            throw new IllegalArgumentException("Progress must be 0 or between 12.5 and 100 in increments of 12.5%");
        }
    }

    public Progress nextPhase() {
        if (percentage >= 100) {
            return this;
        }
        double nextPercentage = Math.min(100, percentage + 12.5);
        return new Progress(nextPercentage);
    }

    @JsonValue
    public double getPercentage() {
        return percentage;
    }
}

