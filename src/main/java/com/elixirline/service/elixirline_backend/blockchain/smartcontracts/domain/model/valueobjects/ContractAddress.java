package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ContractAddress(String contractAddress) {
    public ContractAddress {
    }

    @JsonValue
    public String getContractAddress() {
        return contractAddress;
    }

    @JsonCreator
    public static ContractAddress from(String contractAddress) {
        return new ContractAddress(contractAddress);
    }
}
