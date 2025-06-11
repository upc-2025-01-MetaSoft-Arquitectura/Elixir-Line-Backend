package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateCampaignResource(
        String name,
        String year,
        LocalDate startDate,
        LocalDate endDate
) {
}
