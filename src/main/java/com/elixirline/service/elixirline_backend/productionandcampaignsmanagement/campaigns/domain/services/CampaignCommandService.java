package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates.Campaign;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.CreateCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.DeleteCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.PatchCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.UpdateCampaignCommand;

import java.util.Optional;

public interface CampaignCommandService {
    Optional<Campaign> handle(CreateCampaignCommand command);
    void handle(DeleteCampaignCommand command);
    Optional<Campaign> handle(UpdateCampaignCommand command);
    Optional<Campaign> handle(PatchCampaignCommand command);
}
