package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates.Campaign;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.CampaignResource;

public class CampaignResourceFromEntityAssembler {
    public static CampaignResource toResourceFromEntity(Campaign entity) {
        return new CampaignResource(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getDriverUserId(),
                entity.getLots(),
                entity.getStatus().name(),
                entity.getStartDate(),
                entity.getEndDate()
        );
    }
}
