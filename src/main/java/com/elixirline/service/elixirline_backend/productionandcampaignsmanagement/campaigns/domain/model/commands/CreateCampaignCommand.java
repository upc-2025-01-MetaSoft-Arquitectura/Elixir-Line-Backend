package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands;

import java.time.LocalDate;

public record CreateCampaignCommand(
        String name,
        String age,
        Long driverUserId,
        Long lots,
        String status,
        LocalDate startDate,
        LocalDate endDate
) {
}
