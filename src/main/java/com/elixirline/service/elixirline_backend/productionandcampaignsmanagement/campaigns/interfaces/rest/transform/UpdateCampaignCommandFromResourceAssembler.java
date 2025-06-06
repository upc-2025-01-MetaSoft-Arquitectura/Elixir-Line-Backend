package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.UpdateCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.UpdateCampaignResource;

public class UpdateCampaignCommandFromResourceAssembler {
    public static UpdateCampaignCommand toCommandFromResource(Long campaignId, UpdateCampaignResource resource) {
        return new UpdateCampaignCommand(
                campaignId,
                resource.name(),
                resource.age(),
                resource.startDate(),
                resource.endDate()
        );
    }
}
