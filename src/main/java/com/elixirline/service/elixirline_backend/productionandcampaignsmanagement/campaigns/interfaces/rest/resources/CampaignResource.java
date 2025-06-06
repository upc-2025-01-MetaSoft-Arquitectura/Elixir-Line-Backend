package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources;

import java.time.LocalDate;

public record CampaignResource(
        Long id,
        String name,
        String age,
        Long driverUserId,
        Long lots,
        String status,
        LocalDate startDate,
        LocalDate endDate
) {
}
