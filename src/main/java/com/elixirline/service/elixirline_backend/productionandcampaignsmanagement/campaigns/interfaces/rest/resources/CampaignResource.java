package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources;

import java.time.LocalDate;

public record CampaignResource(
        Long id,
        String name,
        String year,
        Long winegrowerId,
        Long batches,
        String status,
        LocalDate startDate,
        LocalDate endDate
) {
}
