package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StageResource(
        @JsonProperty("batchId") Long batchId,
        @JsonProperty("stageId") Long stageId,
        @JsonProperty("stageName") String stageName,
        @JsonProperty("startDate") long startDate,
        @JsonProperty("endDate") long endDate,
        @JsonProperty("dataHash") String dataHash
) { }
