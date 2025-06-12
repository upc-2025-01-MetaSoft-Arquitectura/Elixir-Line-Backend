package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands;

import java.time.LocalDate;

public record UpdateCampaignCommand(
        Long campaignId,
        String name,
        String year,
        LocalDate startDate,
        LocalDate endDate
) {
}
