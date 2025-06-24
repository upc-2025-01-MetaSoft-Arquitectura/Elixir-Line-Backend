package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ContractName(String contractName) {
    public ContractName {
        if (contractName != null && contractName.length() > 120) {
            throw new IllegalArgumentException("Contract name cannot be longer than 120 characters.");
        }
    }

    @JsonValue
    public String getContractName() {
        return contractName;
    }
}
