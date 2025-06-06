package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands;

public record CreateWineBatchCommand(
        String internalCode,
        String receptionDate,
        String campaign,
        String vineyard,
        String grapeVariety,
        Double initialGrapeQuantityKg,
        String createdBy
) { }
