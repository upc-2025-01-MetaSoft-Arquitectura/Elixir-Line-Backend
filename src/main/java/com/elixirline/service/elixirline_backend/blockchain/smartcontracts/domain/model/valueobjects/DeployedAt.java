package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record DeployedAt(LocalDateTime deployedAt) {
    public DeployedAt {
    }

    @JsonValue
    public String getDeployedAt() {
        return deployedAt.toString();
    }

    @JsonCreator
    public static DeployedAt from(String deployedAt) {
        return new DeployedAt(LocalDateTime.parse(deployedAt));
    }
}
