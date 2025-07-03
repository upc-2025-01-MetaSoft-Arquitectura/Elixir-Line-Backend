package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands;

import java.time.LocalDate;

public record PatchCampaignCommand(
        Long campaignId,
        String name,
        String year,
        Long winegrowerId,
        Long batches,
        String status,
        LocalDate startDate,
        LocalDate endDate
) {
}
