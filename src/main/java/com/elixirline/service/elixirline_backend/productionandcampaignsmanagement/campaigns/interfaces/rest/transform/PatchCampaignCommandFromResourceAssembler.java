package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.PatchCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.PatchCampaignResource;

public class PatchCampaignCommandFromResourceAssembler {
    public static PatchCampaignCommand toCommandFromResource(Long id, PatchCampaignResource resource) {
        return new PatchCampaignCommand(
                id,
                resource.name(),
                resource.year(),
                resource.winegrowerId(),
                resource.batches(),
                resource.status(),
                resource.startDate(),
                resource.endDate()
        );
    }
}
