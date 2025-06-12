package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.CreateCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.CreateCampaignResource;

public class CreateCampaignCommandFromResourceAssembler {
    public static CreateCampaignCommand toCommandFromResource(CreateCampaignResource resource) {
        return new CreateCampaignCommand(
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
