package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates.Campaign;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetAllCampaignsQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignByIdQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignByNameQuery;

import java.util.List;
import java.util.Optional;

public interface CampaignQueryService {
    List<Campaign> handle(GetAllCampaignsQuery query);
    Optional<Campaign> handle(GetCampaignByIdQuery query);
    Optional<Campaign> handle(GetCampaignByNameQuery query);
}
