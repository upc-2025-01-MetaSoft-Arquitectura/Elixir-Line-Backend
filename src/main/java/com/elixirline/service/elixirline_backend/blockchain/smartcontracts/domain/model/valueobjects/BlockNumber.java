package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record BlockNumber(BigInteger blockNumber) {
    public BlockNumber {
        if (blockNumber == null) {
            throw new IllegalArgumentException("Block number cannot be null");
        }
    }

    @JsonValue
    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    @JsonCreator
    public static BlockNumber from(BigInteger blockNumber) {
        return new BlockNumber(blockNumber);
    }
}
