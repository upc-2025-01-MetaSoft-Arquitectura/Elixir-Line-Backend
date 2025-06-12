package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CreateCampaignResource(
        @Schema(description = "Nombre de la campaña", example = "Campaña Invierno 2025")
        String name,
        @Schema(description = "Año de la campaña", example = "2025")
        String year,
        @Schema(description = "ID del conductor asociado", example = "1")
        Long winegrowerId,
        @Schema(description = "Número de lotes en la campaña", example = "12")
        Long batches,
        @Schema(
                description = "Estado de la campaña",
                example = "EN_PROCESO",
                allowableValues = {"EN_PROCESO", "FINALIZADA", "PENDIENTE"}
        )
        String status,
        @Schema(description = "Fecha de inicio (formato yyyy-MM-dd)", example = "2025-06-01")
        LocalDate startDate,
        @Schema(description = "Fecha de finalización (formato yyyy-MM-dd)", example = "2025-06-10")
        LocalDate endDate
) {

}
