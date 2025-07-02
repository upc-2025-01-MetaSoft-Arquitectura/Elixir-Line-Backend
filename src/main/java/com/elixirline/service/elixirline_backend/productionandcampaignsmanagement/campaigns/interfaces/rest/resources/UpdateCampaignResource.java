package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateCampaignResource(
        @NotBlank(message = "El nombre no puede estar vacío.")
        String name,
        @NotBlank(message = "El año no puede estar vacío.")
        String year,
        @NotNull(message = "El ID del viticultor es obligatorio.")
        Long winegrowerId,
        @NotNull(message = "La cantidad de lotes es obligatoria.")
        Long batches,
        @NotBlank(message = "El estado es obligatorio.")
        String status,
        @NotNull(message = "La fecha de inicio es obligatoria.")
        LocalDate startDate,
        @NotNull(message = "La fecha de fin es obligatoria.")
        LocalDate endDate
) {
}
