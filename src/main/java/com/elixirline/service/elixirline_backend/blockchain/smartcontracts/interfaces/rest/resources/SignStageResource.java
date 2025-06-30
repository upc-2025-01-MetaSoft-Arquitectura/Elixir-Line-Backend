package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record SignStageResource(
        @JsonProperty("batchId") Long batchId,
        @JsonProperty("stageId") Long stageId,
        @JsonProperty("stageName") String stageName,
        @JsonProperty("startDate") LocalDate startDate,
        @JsonProperty("endDate") LocalDate endDate,
        @JsonProperty("dataHash") String dataHash
) {}