package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record TankCode(String tankCode) {
    public TankCode {
        if ( tankCode != null && tankCode.length() > 30) {
            throw new IllegalArgumentException("Tank code cannot be longer than 30 characters.");
        }
    }


    @JsonValue
    public String getTankCode() {
        return tankCode;
    }

    @JsonCreator
    public static TankCode from(String tankCode) {
        return new TankCode(tankCode);
    }
}
