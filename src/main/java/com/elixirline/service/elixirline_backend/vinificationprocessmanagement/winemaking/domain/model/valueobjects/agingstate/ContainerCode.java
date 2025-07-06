package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ContainerCode(String containerCode) {
    public ContainerCode {
        if (containerCode != null && containerCode.length() > 30) {
            throw new IllegalArgumentException("Container code cannot be longer than 30 characters.");
        }
    }

    @JsonValue
    public String getContainerCode() {
        return containerCode;
    }

    @JsonCreator
    public static ContainerCode from(String containerCode) {
        return new ContainerCode(containerCode);
    }
}