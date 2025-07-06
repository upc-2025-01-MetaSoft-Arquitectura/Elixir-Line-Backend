package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Material(String material) {
    public Material {
        if (material != null && material.length() > 50) {
            throw new IllegalArgumentException("Material cannot be longer than 50 characters.");
        }
    }

    @JsonValue
    public String getMaterial() {
        return material;
    }

    @JsonCreator
    public static Material from(String material) {
        return new Material(material);
    }
}